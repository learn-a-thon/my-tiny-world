package com.example.notification.exception;

import com.example.notification.define.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage implements CommonType<HttpStatus, String> {

    /** 200 response **/
    OK(HttpStatus.OK, "요청한 응답이 성공적으로 수행됐습니다."),
    CREATED(HttpStatus.CREATED, "성공적으로 생성되었습니다."),

    /** 3xx response **/
    FOUND(HttpStatus.FOUND, "요청한 resources 를 찾음"),
    NOT_MODIFIED(HttpStatus.NOT_MODIFIED, "변경사항 없음."),

    /** 4xx response **/
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않음."),
    CONFLICT(HttpStatus.CONFLICT, "요청이 현재 서버의 상태와 충돌됨."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없음."),
    PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "요청 본문이 너무 큼."),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "제한된 시간에 너무 많은 요청."),

    /** 5xx response **/
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러."),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "구현되지 않음."),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "잘못된 응답 수신."),
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "시간초과"),
    BANDWIDTH_LIMIT_EXCEEDED(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "대역폭 제한.");


    private final HttpStatus httpStatus;
    private final String desc;

    public HttpStatus getCode() {
        return this.httpStatus;
    }

    public static String getResponseMessageBy(HttpStatus httpStatus) {
        Optional<ExceptionMessage> message = Arrays.stream(ExceptionMessage.values())
                .filter(exMessage -> httpStatus.equals(exMessage.getCode()))
                .findAny();

        return message.isPresent() ? message.get().getDesc() : "";
    }
}
