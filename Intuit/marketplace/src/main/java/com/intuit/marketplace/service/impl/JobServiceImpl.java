package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.dto.request.JobRequest;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.enums.JobType;
import com.intuit.marketplace.exception.JobNotFoundException;
import com.intuit.marketplace.mapper.JobDtoMapper;
import com.intuit.marketplace.repository.JobRepository;
import com.intuit.marketplace.service.AuctionMediator;
import com.intuit.marketplace.service.JobService;
import com.intuit.marketplace.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;
    private final UserService userService;
    private final AuctionMediator auctionMediator;

    public JobServiceImpl(JobRepository jobRepository, UserService userService, AuctionMediator auctionMediator) {
        this.jobRepository = jobRepository;
        this.userService = userService;
        this.auctionMediator = auctionMediator;
    }

    @Override
    public Page<Job> getJobs(JobType jobType, JobStatus jobStatus, String userId, String sortBy, String order,
                             int page, int size, LocalDateTime expiringBefore) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortBy));
        Specification<Job> spec = buildSpecification(jobType, jobStatus, userId, expiringBefore);
        logger.info("Fetching jobs with type: {}, userId: {}, sortBy: {}, order: {}, page: {}, size: {}", jobType,
                userId, sortBy, order, page, size);
        return jobRepository.findAll(spec, pageable);
    }

    @Override
    public Job postNewJob(JobRequest jobRequest) {
        logger.info("Posting new job for userId: {}", jobRequest.getUserId());
        User user = userService.getUser(jobRequest.getUserId());

        Job job = JobDtoMapper.mapToJob(jobRequest);
        job.setPoster(user);
        job.setJobStatus(JobStatus.OPEN);
        job.setBidCount(0);

        Job savedJob = jobRepository.save(job);
        logger.info("Job with id {} posted successfully", savedJob.getId());
        return savedJob;
    }

    @Override
    public Job getJobById(Long id) {
        logger.info("Fetching job with id: {}", id);
        return jobRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Job with id {} not found", id);
                    return new JobNotFoundException("Job with id " + id + " not found", "NOT_FOUND");
                });
    }

    @Override
    public void closeJob(Long jobId) {
        auctionMediator.closeBiddingForJob(jobId);
    }

    private Specification<Job> buildSpecification(JobType jobType, JobStatus jobStatus, String userId, LocalDateTime expiringBefore) {
        return (Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (jobType != null) {
                predicates.add(criteriaBuilder.equal(root.get("jobType"), jobType));
            }
            if (jobStatus != null) {
                predicates.add(criteriaBuilder.equal(root.get("jobStatus"), jobStatus));
            }
            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("poster").get("id"), Long.parseLong(userId)));
            }
            if (expiringBefore != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("expirationDate"), expiringBefore));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}