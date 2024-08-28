package com.intuit.marketplace.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.intuit.marketplace.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JobRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String requirements;
    @NotNull
    private JobType jobType;
    @NotNull
    private BigDecimal startingPrice;
    @NotNull
    private LocalDateTime expirationDate;
    @NotNull
    private Long userId;
}
