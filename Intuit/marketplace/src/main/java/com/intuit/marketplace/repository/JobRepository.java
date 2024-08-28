package com.intuit.marketplace.repository;


import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.enums.JobStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findAll(Specification<Job> spec, Pageable pageable);

    @Query("SELECT j.id FROM Job j WHERE j.jobStatus = :status AND j.expirationDate < :currentDateTime")
    List<Long> findExpiredJobIds(@Param("currentDateTime") LocalDateTime currentDateTime,
                                 @Param("status") JobStatus status);

}