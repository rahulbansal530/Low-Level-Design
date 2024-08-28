package com.intuit.marketplace.validator;

import com.intuit.marketplace.constant.AppConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PriceConstraintValidator implements ConstraintValidator<PriceConstraint, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal price, ConstraintValidatorContext constraintValidatorContext) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0 &&
                price.compareTo(BigDecimal.valueOf(AppConstants.MAX_BIDDING_PRICE)) < 0;
    }
}
