package com.yunbok.searchapi.v1.authentication.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccessTokenRequest {

    private String account;

    private AccessTokenRequest(String account) {
        this.account = account;
    }

    public static AccessTokenRequest requestOf(String account) {
        return new AccessTokenRequest(account);
    }
}
