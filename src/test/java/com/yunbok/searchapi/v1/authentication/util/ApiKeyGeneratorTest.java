package com.yunbok.searchapi.v1.authentication.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ApiKeyGeneratorTest {


    @Mock
    private ApiKeyService apiKeyService;

    @InjectMocks
    private ApiKeyGenerator apiKeyGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiKeyGenerator = new ApiKeyGenerator(apiKeyService);
    }

    /**
     * 만약 동일한 apiKey가 존재하면, 새로운 apiKey를 반환한다.
     */
    @Test
    void ensureUniqueApiKey_generatesUniqueApiKey() {

        // given
        String apiKey = "abcdefghijklmnopqrstuvwxzy0123456789";

        // when
        when(apiKeyService.isExistsApiKey(apiKeyGenerator.getHashedApiKey(apiKey))).thenReturn(true);
        String uniqueApiKey = apiKeyGenerator.ensureUniqueApiKey(apiKey);

        // then
        assertNotNull(uniqueApiKey);
        assertNotEquals(apiKey, uniqueApiKey);
    }

    /**
     * 1. SHA-256 해시는 64자여야만 한다.
     * 2. api key를 해시하면 이전에 해시된 api key와 동일해야 한다.
     */
    @Test
    public void testGetHashedApiKey() {
        // given
        String apiKey = "test";

        // when
        String hashedApiKey = apiKeyGenerator.getHashedApiKey(apiKey);

        // then
        assertNotNull(hashedApiKey);
        assertEquals(64, hashedApiKey.length());
        assertEquals(apiKeyGenerator.getHashedApiKey(apiKey), hashedApiKey);
    }
}