package com.example.exception;

public class NotFoundNotificationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Notification 정보가 존재하지 않습니다. id : ";
    private static final Integer DEFAULT_ID = 0;

    public NotFoundNotificationException(String message) {
        super(message);
    }

    public NotFoundNotificationException() {
        super(DEFAULT_MESSAGE + DEFAULT_ID);
    }

    public NotFoundNotificationException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }

}
