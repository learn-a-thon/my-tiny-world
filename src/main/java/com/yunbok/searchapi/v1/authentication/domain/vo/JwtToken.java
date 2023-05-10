package com.yunbok.searchapi.v1.authentication.domain.vo;

public record JwtToken(
        String accessToken,
        long expiredTime,
        String tokenType) {

    public static JwtToken generateOf(String accessToken, long expiredTime) {
        return new JwtToken(accessToken, expiredTime, "Bearer");
    }
}
