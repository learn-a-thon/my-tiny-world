package com.yunbok.searchapi.v1.authentication.util;

import com.yunbok.searchapi.v1.authentication.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    // 비밀 키
    private final String secretKey = "mySecretKey123";

    // JWT 토큰 유효시간 30분
    private final long validityInMilliseconds = 1800000;

    public String createToken(User user) {
        // JWT 토큰 만료 시간 설정
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        // JWT 토큰 생성
        return Jwts.builder()
                .setSubject(user.getAccount())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
