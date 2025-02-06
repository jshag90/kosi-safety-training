package com.kosi.exception;

public class ReusedRefreshTokenException extends Throwable {
    public ReusedRefreshTokenException(String message) {
        super(message);
    }
}
