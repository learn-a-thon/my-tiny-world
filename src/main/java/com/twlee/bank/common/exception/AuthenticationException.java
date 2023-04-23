package com.twlee.bank.common.exception;

public class AuthenticationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "인증 업무를 정상적으로 처리하지 못했습니다. 잠시 후 다시 시도해주세요.";

    public AuthenticationException() {
        super(DEFAULT_MESSAGE);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    protected AuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
