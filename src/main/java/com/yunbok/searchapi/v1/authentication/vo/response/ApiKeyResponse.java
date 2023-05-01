package com.yunbok.searchapi.v1.authentication.vo.response;

import com.yunbok.searchapi.v1.common.define.ResponseCode;

public record ApiKeyResponse(
        int code,
        String message,
        String apikey
) {
    public static ApiKeyResponse successOf(String key) {
        return new ApiKeyResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), key);
    }
}
