package com.intuit.marketplace.exception;

import lombok.Getter;

@Getter
public class WrongOwnerException extends RuntimeException {
    private final String errorCode;

    public WrongOwnerException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}