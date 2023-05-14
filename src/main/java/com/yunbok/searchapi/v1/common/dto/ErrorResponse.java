package com.yunbok.searchapi.v1.common.dto;

import com.yunbok.searchapi.v1.common.define.ResponseCode;

public record ErrorResponse (
        int code,
        String message
)
{
    public static ErrorResponse responseOf(ResponseCode responseCode) {
        return new ErrorResponse(responseCode.getCode(), responseCode.getMessage());
    }

    public static ErrorResponse responseOf(ResponseCode responseCode, String message) {
        return new ErrorResponse(responseCode.getCode(), message);
    }
}