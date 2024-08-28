package com.intuit.marketplace.controller;

import com.intuit.marketplace.dto.request.JobRequest;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.enums.JobType;
import com.intuit.marketplace.service.JobService;
import com.intuit.marketplace.service.WinnerSelectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private WinnerSelectionService winnerSelectionService;

    @GetMapping
    public ResponseEntity<Page<Job>> getJobs(@RequestParam(value = "jobType", required = false) JobType jobType,
                                             @RequestParam(value = "jobStatus", required = false) JobStatus jobStatus,
                                             @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
                                             @RequestParam(value = "expiringBefore") LocalDateTime exipiringBefore,
                                             @RequestParam(value = "userId", required = false) String userId,
                                             @RequestParam(value = "order", defaultValue = "desc") String order,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<Job> jobs = jobService.getJobs(jobType, jobStatus, userId, sortBy, order, page, size, exipiringBefore);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<Job> postNewJob(@Valid @RequestBody JobRequest jobRequest) {
        Job savedJob = jobService.postNewJob(jobRequest);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobDetails(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping("/{jobId}/close")
    public ResponseEntity<Object> closeJob(@PathVariable Long jobId) {
        jobService.closeJob(jobId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{jobId}/winner")
    public ResponseEntity<User> getWinner(@PathVariable Long jobId) {
        User user = winnerSelectionService.selectWinner(jobId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
