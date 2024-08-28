package com.intuit.marketplace.exception;

import lombok.Getter;

@Getter
public class JobNotFoundException extends RuntimeException {
    private final String errorCode;

    public JobNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}