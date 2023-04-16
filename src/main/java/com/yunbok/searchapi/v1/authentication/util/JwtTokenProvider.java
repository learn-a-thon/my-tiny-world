package com.yunbok.searchapi.v1.authentication.util;

import com.yunbok.searchapi.v1.authentication.dto.response.AccessTokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public AccessTokenResponse generateJwtToken(String account) {
        return doGenerateToken(account, secretKey, expirationTime);
    }

    private AccessTokenResponse doGenerateToken(String subject, String secretKey, long expirationTime) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiration = new Date(now + expirationTime * 1000);

        String jwtToken = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getKey(secretKey))
                .compact();

        return AccessTokenResponse.responseOf(jwtToken, expiration.getTime());
    }

    private Key getKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
