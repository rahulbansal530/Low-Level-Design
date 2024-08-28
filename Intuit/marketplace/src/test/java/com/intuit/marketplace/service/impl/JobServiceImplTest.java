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
import com.intuit.marketplace.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private UserService userService;

    @Mock
    private AuctionMediator auctionMediator;

    @InjectMocks
    private JobServiceImpl jobService;

    private Job job;
    private User user;

    @BeforeEach
    void setUp() {
        job = new Job();
        job.setId(1L);
        user = new User();
        user.setId(1L);
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetJobs() {
//        JobType jobType = JobType.MARKETING;
//        JobStatus jobStatus = JobStatus.OPEN;
//        String userId = "1";
//        String sortBy = "date";
//        String order = "ASC";
//        int page = 0;
//        int size = 10;
//
//        Page<Job> jobPage = new PageImpl<>(List.of(job));
//
//        when(jobRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(jobPage);
//
//        Page<Job> result = jobService.getJobs(jobType, jobStatus, userId, sortBy, order, page, size);
//
//        assertEquals(1, result.getTotalElements());
//        assertEquals(job, result.getContent().get(0));
//    }

    @Test
    void testPostNewJob() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setUserId(1L);
        Job job = new Job();
        job.setId(1L);

        when(userService.getUser(any())).thenReturn(user);
        mockStatic(JobDtoMapper.class);
        when(JobDtoMapper.mapToJob(any(JobRequest.class))).thenReturn(job);
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job savedJob = jobService.postNewJob(jobRequest);

        verify(userService).getUser(jobRequest.getUserId());
        verify(jobRepository).save(job);
        assertEquals(job, savedJob);
    }

    @Test
    void testGetJobById_Success() {
        Long jobId = 1L;

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

        Job result = jobService.getJobById(jobId);

        verify(jobRepository).findById(jobId);
        assertEquals(job, result);
    }

    @Test
    void testGetJobById_NotFound() {
        Long jobId = 1L;

        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());

        JobNotFoundException exception = assertThrows(JobNotFoundException.class, () -> {
            jobService.getJobById(jobId);
        });

        assertTrue(exception.getMessage().contains("Job with id " + jobId + " not found"));
    }

    @Test
    void testCloseJob() {
        Long jobId = 1L;

        doNothing().when(auctionMediator).closeBiddingForJob(jobId);

        jobService.closeJob(jobId);

        verify(auctionMediator).closeBiddingForJob(jobId);
    }
}
