package com.intuit.marketplace.mapper;

import com.intuit.marketplace.dto.request.JobRequest;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.enums.JobType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JobDtoMapperTest {

    @Test
    void testMapToJob() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobType(JobType.IT);
        jobRequest.setTitle("Software Engineer");
        jobRequest.setDescription("Develop and maintain software applications.");
        jobRequest.setExpirationDate(LocalDateTime.of(2025, 12, 31, 23, 59));
        jobRequest.setRequirements("5+ years of experience in Java.");
        jobRequest.setStartingPrice(BigDecimal.valueOf(50000.00));

        Job job = JobDtoMapper.mapToJob(jobRequest);

        assertEquals(JobType.IT, job.getJobType());
        assertEquals("Software Engineer", job.getTitle());
        assertEquals("Develop and maintain software applications.", job.getDescription());
        assertEquals(LocalDateTime.of(2025, 12, 31, 23, 59), job.getExpirationDate());
        assertEquals("5+ years of experience in Java.", job.getRequirements());
        assertEquals(BigDecimal.valueOf(50000.00), job.getStartingPrice());
    }

    @Test
    void testMapToJobWithNullValues() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobType(null);
        jobRequest.setTitle(null);
        jobRequest.setDescription(null);
        jobRequest.setExpirationDate(null);
        jobRequest.setRequirements(null);
        jobRequest.setStartingPrice(null);

        Job job = JobDtoMapper.mapToJob(jobRequest);

        assertNull(job.getJobType());
        assertNull(job.getTitle());
        assertNull(job.getDescription());
        assertNull(job.getExpirationDate());
        assertNull(job.getRequirements());
        assertNull(job.getStartingPrice());
    }
}
