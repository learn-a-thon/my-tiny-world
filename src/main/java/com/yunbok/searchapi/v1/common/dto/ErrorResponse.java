package com.yunbok.searchapi.v1.common.dto;

import com.yunbok.searchapi.v1.common.define.ResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse implements Response {

    private int code;
    private String message;

    private ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse responseOf(ResponseCode responseCode) {
        return new ErrorResponse(responseCode.getCode(), responseCode.getMessage());
    }

    public static ErrorResponse responseOf(ResponseCode responseCode, String message) {
        return new ErrorResponse(responseCode.getCode(), message);
    }
}
