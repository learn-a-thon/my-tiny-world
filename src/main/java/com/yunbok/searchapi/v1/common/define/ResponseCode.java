package com.yunbok.searchapi.v1.common.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(HttpStatus.OK, 200, "ok"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, 400, "invalid request"),
    INVALID_USER_INFO(HttpStatus.BAD_REQUEST, 401, "invalid user info"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "server error");

    private final HttpStatus status;
    private final int code;
    private final String message;


}
