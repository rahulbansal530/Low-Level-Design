package com.intuit.marketplace.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.enums.JobType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(max = 16384, message = "Must be 16KB or less")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(max = 16384, message = "Must be 16KB or less")
    private String requirements;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus jobStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "poster_id", nullable = false)
    private User poster;

    @Column
    private BigDecimal lowestBidPrice;

    @Version
    @JsonIgnore
    private Long version;

    @Column(nullable = false)
    private Integer bidCount = 0;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private BigDecimal startingPrice;

    @ManyToOne
    @JoinColumn(name = "winning_bidder_id")
    private User winningBidder;

    @Column
    private BigDecimal winningBidAmount;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
