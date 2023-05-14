package com.yunbok.searchapi.v1.authentication.presentation.response;

public record ApiKeyResponse(
        int code,
        String message,
        String apikey
) {}
