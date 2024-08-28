package com.intuit.marketplace.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.intuit.marketplace.validator.PriceConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BidRequest {

    @NotNull
    @PriceConstraint
    private BigDecimal bidPrice;

    @NotNull
    private Long userId;

}
