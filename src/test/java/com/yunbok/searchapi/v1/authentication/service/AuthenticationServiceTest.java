package com.yunbok.searchapi.v1.authentication.service;

import com.yunbok.searchapi.v1.authentication.dto.request.AccessTokenRequest;
import com.yunbok.searchapi.v1.authentication.dto.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.dto.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.dto.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.entity.ApiKey;
import com.yunbok.searchapi.v1.authentication.repository.ApiKeyRepository;
import com.yunbok.searchapi.v1.authentication.util.ApiKeyGenerator;
import com.yunbok.searchapi.v1.authentication.entity.User;
import com.yunbok.searchapi.v1.authentication.util.JwtTokenProvider;
import com.yunbok.searchapi.v1.common.repository.UserRepository;
import com.yunbok.searchapi.v1.authentication.util.ApiKeyUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApiKeyGenerator apiKeyGenerator;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeAll
    public void setup() {
        mockStatic(ApiKeyUtil.class);
    }

    @Test
    public void testGetApiKey() throws Exception {
        // given
        String account = "testAccount";
        String password = "testPassword";
        String apiKey = "testApiKey";
        User user = new User(1L, account, password);
        ApiKeyRequest request = ApiKeyRequest.requestOf(account, password);

        // when
        when(userRepository.findByAccountAndPassword(account, password)).thenReturn(Optional.of(user));
        when(apiKeyGenerator.generateApiKey()).thenReturn(apiKey);
        ApiKeyResponse response = authenticationService.getApiKey(request);
        verify(apiKeyRepository).save(refEq(ApiKey.save(user, apiKeyGenerator.getHashedApiKey(apiKey))));

        when(userRepository.findByAccountAndPassword(account, password)).thenReturn(user);
        when(ApiKeyUtil.generateApiKey()).thenReturn(apiKey);

        ApiKeyRequest request = ApiKeyRequest.requestOf(account, password);

        // when
        ApiKeyResponse response = authenticationService.getApiKey(request);
        verify(apiKeyRepository).save(refEq(ApiKey.save(user, ApiKeyUtil.getHashedApiKey(apiKey))));

        // then
        assertEquals(apiKey, response.getApiKey());
    }

    @Test
    public void testGetAccessToken() {
        // given
        String apiKey = "testApiKey";
        String account = "testAccount";
        String password = "password";
        AccessTokenRequest request = AccessTokenRequest.requestOf(account);
        User user = new User(1L, account, password);
        ApiKey apiKeyEntity = ApiKey.save(user, apiKey);
        AccessTokenResponse expectedResponse = AccessTokenResponse.responseOf("testToken", 100000L);

        // when
        when(apiKeyRepository.findByApiKey(any())).thenReturn(Optional.of(apiKeyEntity));
        when(jwtTokenProvider.generateJwtToken(any())).thenReturn(expectedResponse);
        AccessTokenResponse actualResponse = authenticationService.getAccessToken(apiKey, request);

        // then
        assertNotNull(actualResponse);
        assertEquals(actualResponse.getAccessToken(), expectedResponse.getAccessToken());
    }
}