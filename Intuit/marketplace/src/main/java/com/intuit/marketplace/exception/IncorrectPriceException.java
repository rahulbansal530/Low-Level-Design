package com.intuit.marketplace.exception;

import lombok.Getter;

@Getter
public class IncorrectPriceException extends RuntimeException {
    private final String errorCode;

    public IncorrectPriceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}