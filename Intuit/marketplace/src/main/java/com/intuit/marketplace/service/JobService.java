package com.intuit.marketplace.service;

import com.intuit.marketplace.dto.request.JobRequest;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.enums.JobType;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface JobService {

    Job getJobById(Long id);

    Job postNewJob(JobRequest jobRequest);

    Page<Job> getJobs(JobType jobType, JobStatus jobStatus, String userId, String sortBy, String order, int page, int size, LocalDateTime expiringBefore);

    void closeJob(Long jobId);
}