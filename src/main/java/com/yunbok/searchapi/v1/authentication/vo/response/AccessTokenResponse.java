package com.yunbok.searchapi.v1.authentication.vo.response;

import com.yunbok.searchapi.v1.common.define.ResponseCode;

public record AccessTokenResponse (
        int code,
        String message,
        String accessToken,
        long expiredTime,
        String tokenType
) {
    public static AccessTokenResponse generateTokenOf(String accessToken, long expiredTime) {
        return new AccessTokenResponse(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMessage(),
                accessToken,
                expiredTime,
                "Bearer");
    }
}
