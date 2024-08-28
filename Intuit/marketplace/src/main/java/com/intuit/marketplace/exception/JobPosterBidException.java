package com.intuit.marketplace.exception;

import lombok.Getter;

@Getter
public class JobPosterBidException extends RuntimeException {
    private final String errorCode;

    public JobPosterBidException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}