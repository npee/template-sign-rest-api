package com.npee.oauth2.sign.advice.exception;

public class CustomUserSigninFailedException extends RuntimeException {
    public CustomUserSigninFailedException() {
        super();
    }

    public CustomUserSigninFailedException(String message) {
        super(message);
    }

    public CustomUserSigninFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
