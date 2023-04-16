package com.twlee.bank.member.exception;

public class MemberException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "회원 업무를 정상적으로 처리하지 못했습니다. 잠시 후 다시 시도해주세요.";

    public MemberException() {
        super(DEFAULT_MESSAGE);
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberException(Throwable cause) {
        super(cause);
    }

    protected MemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
