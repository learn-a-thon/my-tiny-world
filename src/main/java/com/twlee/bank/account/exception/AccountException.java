package com.twlee.bank.account.exception;

public class AccountException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "계좌 업무를 정상적으로 처리하지 못했습니다. 잠시 후 다시 시도해주세요.";

    public AccountException() {
        super(DEFAULT_MESSAGE);
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }

    protected AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
