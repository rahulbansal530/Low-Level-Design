package com.intuit.marketplace.exception;

import lombok.Getter;

@Getter
public class JobClosedException extends RuntimeException {
    private final String errorCode;

    public JobClosedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}