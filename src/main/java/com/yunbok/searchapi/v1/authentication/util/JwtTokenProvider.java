package com.yunbok.searchapi.v1.authentication.util;

import com.yunbok.searchapi.v1.authentication.domain.vo.JwtToken;
import com.yunbok.searchapi.v1.authentication.presentation.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.exception.ApiAuthenticationException;
import com.yunbok.searchapi.v1.common.define.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
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

    public JwtToken generateJwtToken(String account) {
        return doGenerateToken(account, secretKey, expirationTime);
    }

    public void validateToken(String token) throws ApiAuthenticationException {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getKey(secretKey))
                    .build()
                    .parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                throw new JwtException("invalid access token");
            }
        } catch (JwtException e) {
            throw new ApiAuthenticationException("invalid access token", ResponseCode.INVALID_REQUEST);
        }
    }

    private JwtToken doGenerateToken(String subject, String secretKey, long expirationTime) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiration = new Date(now + expirationTime * 1000);

        String jwtToken = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getKey(secretKey))
                .compact();

        return JwtToken.generateOf(jwtToken, expiration.getTime());
    }

    private Key getKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}