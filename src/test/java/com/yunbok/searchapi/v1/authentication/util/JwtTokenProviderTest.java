package com.yunbok.searchapi.v1.authentication.util;

import com.yunbok.searchapi.v1.authentication.domain.vo.JwtToken;
import com.yunbok.searchapi.v1.authentication.presentation.response.AccessTokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private Key key;

    @BeforeEach
    public void setUp() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        key = Keys.hmacShaKeyFor(keyBytes);    }

    /**
     * jwt token 테스트
     * 1. Claim이 정상적으로 파싱이 되는지 확인
     * 2. Claim에 저장한 id 값이 정상적으로 파싱이 되는지 확인
     */
    @Test
    void testGenerateJwtToken() {
        // given
        String account = "test123";

        // when
        JwtToken jwtToken = jwtTokenProvider.generateJwtToken(account);

        // then
        assertNotNull(jwtToken);
        assertNotNull(jwtToken.accessToken());
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken.accessToken());
        assertEquals(account, claimsJws.getBody().getSubject());
    }
}