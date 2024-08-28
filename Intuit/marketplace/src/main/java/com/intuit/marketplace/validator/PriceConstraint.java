package com.intuit.marketplace.validator;

import com.intuit.marketplace.constant.AppConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriceConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceConstraint {
    String message() default "Price must be greater than 0 and less than " + AppConstants.MAX_BIDDING_PRICE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
