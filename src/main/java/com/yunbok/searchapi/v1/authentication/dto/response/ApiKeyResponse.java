package com.yunbok.searchapi.v1.authentication.dto.response;

import com.yunbok.searchapi.v1.common.define.ResponseCode;
import com.yunbok.searchapi.v1.common.dto.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiKeyResponse implements Response {

    private int code;
    private String message;
    private String apiKey;

    private ApiKeyResponse(int code, String message, String apiKey) {
        this.code = code;
        this.message = message;
        this.apiKey = apiKey;
    }

    public static ApiKeyResponse successOf(String key) {
        return new ApiKeyResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), key);
    }
}
