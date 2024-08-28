package com.intuit.marketplace.exception;

import lombok.Getter;

@Getter
public class BidNotFoundException extends RuntimeException {
    private final String errorCode;

    public BidNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}