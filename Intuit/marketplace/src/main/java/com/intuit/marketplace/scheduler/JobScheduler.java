package com.intuit.marketplace.scheduler;

import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.repository.JobRepository;
import com.intuit.marketplace.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobScheduler {

    private static final Logger logger = LoggerFactory.getLogger(JobScheduler.class);

    private final JobService jobService;
    private final JobRepository jobRepository;

    public JobScheduler(JobService jobService, JobRepository jobRepository) {
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void closeExpiredJobs() {
        LocalDateTime now = LocalDateTime.now();
        List<Long> expiredJobIds = jobRepository.findExpiredJobIds(now, JobStatus.OPEN);
        for (Long jobId : expiredJobIds) {
            try {
                jobService.closeJob(jobId);
            } catch (Exception ex) {
                logger.error("Error occurred while closing job with jobId {} : {}", jobId, ex.getMessage());
            }
        }
    }

}
