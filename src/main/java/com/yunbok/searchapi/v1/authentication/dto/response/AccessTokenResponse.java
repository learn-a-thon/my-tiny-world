package com.yunbok.searchapi.v1.authentication.dto.response;

import com.yunbok.searchapi.v1.common.define.ResponseCode;
import com.yunbok.searchapi.v1.common.dto.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenResponse implements Response {

    private int code;
    private String message;
    private String accessToken;
    private String expiredTime;
    private final String tokenType = "Bearer";

    private AccessTokenResponse(ResponseCode responseCode, String accessToken, String expiredTime) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.accessToken = accessToken;
        this.expiredTime = expiredTime;
    }

    public static AccessTokenResponse responseOf(String accessToken, String expiredTime) {
        return new AccessTokenResponse(
                ResponseCode.SUCCESS,
                accessToken,
                expiredTime);
    }
}
