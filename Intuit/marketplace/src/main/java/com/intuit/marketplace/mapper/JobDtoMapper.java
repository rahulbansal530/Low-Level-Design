package com.intuit.marketplace.mapper;

import com.intuit.marketplace.dto.request.JobRequest;
import com.intuit.marketplace.entity.Job;

public class JobDtoMapper {
    public static Job mapToJob(JobRequest jobRequest) {

        return Job.builder()
                .jobType(jobRequest.getJobType())
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .expirationDate(jobRequest.getExpirationDate())
                .requirements(jobRequest.getRequirements())
                .startingPrice(jobRequest.getStartingPrice())
                .build();
    }
}
