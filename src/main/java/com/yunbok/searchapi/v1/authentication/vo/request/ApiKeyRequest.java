package com.yunbok.searchapi.v1.authentication.vo.request;

import jakarta.validation.constraints.NotBlank;

public record ApiKeyRequest (
        @NotBlank
        String account,

        @NotBlank
        String password
) {}
