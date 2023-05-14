package com.yunbok.searchapi.v1.authentication.presentation.response;

public record AccessTokenResponse (
        int code,
        String message,
        String accessToken,
        long expiredTime,
        String tokenType
) {}
