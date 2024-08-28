package com.intuit.marketplace.exception;

import lombok.Getter;

@Getter
public class IncorrectDateException extends RuntimeException {
    private final String errorCode;

    public IncorrectDateException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}