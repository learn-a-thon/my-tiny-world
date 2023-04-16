package com.yunbok.searchapi.v1.authentication.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiKeyUtilTest {

    /**
     * Api Key는 Base64 인코딩 후 32바이트 이여야만 한다.
     *
     * Base64 인코딩은 문자 길이를 1/3만큼 늘리기 때문에 예상 문자 길이는 실제로 44이다.
     * - 32 byte = 256bit, 256/6 = 42.67
     * - Base64 인코딩된 문자열은 4자를 사용하여 3byte 데이터를 나타내므로 문자열 길이는 4의 배수여야만 한다.
     * - 그래서 32 byte면서 44 문자열 길이를 가지고 있다.
     */
    @Test
    public void testGenerateApiKey() {
        String apiKey = ApiKeyUtil.generateApiKey();
        assertNotNull(apiKey);
        assertEquals(44, apiKey.length());
    }

    /**
     * 1. SHA-256 해시는 64자여야만 한다.
     * 2. api key를 해시하면 이전에 해시된 api key와 동일해야 한다.
     */
    @Test
    public void testGetHashedApiKey() {
        String apiKey = "test";
        String hashedApiKey = ApiKeyUtil.getHashedApiKey(apiKey);
        assertNotNull(hashedApiKey);
        assertEquals(64, hashedApiKey.length());
        assertEquals(ApiKeyUtil.getHashedApiKey(apiKey), hashedApiKey);
    }
}