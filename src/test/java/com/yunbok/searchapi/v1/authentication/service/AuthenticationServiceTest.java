package com.yunbok.searchapi.v1.authentication.service;

import com.yunbok.searchapi.v1.authentication.application.AuthenticationService;
import com.yunbok.searchapi.v1.authentication.vo.request.AccessTokenRequest;
import com.yunbok.searchapi.v1.authentication.vo.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.vo.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.vo.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.domain.ApiKey;
import com.yunbok.searchapi.v1.authentication.infrastructure.ApiKeyRepository;
import com.yunbok.searchapi.v1.authentication.domain.User;
import com.yunbok.searchapi.v1.authentication.util.ApiKeyGenerator;
import com.yunbok.searchapi.v1.authentication.util.ApiKeyUtil;
import com.yunbok.searchapi.v1.authentication.util.JwtTokenProvider;
import com.yunbok.searchapi.v1.authentication.infrastructure.UserRepository;
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
        User user = new User(account, password);
        ApiKeyRequest request = new ApiKeyRequest(account, password);

        // when
        when(userRepository.findByAccountAndPassword(account, password)).thenReturn(Optional.of(user));
        when(apiKeyGenerator.generateApiKey()).thenReturn(apiKey);
        ApiKeyResponse response = authenticationService.getApiKey(request);
        verify(apiKeyRepository).save(refEq(new ApiKey(apiKeyGenerator.getHashedApiKey(apiKey))));

        when(userRepository.findByAccountAndPassword(account, password)).thenReturn(Optional.of(user));
        when(ApiKeyUtil.generateApiKey()).thenReturn(apiKey);

        // then
        assertEquals(apiKey, response.apikey());
    }

    @Test
    public void testGetAccessToken() {
        // given
        String apiKey = "testApiKey";
        String account = "testAccount";
        String password = "password";
        AccessTokenRequest request = new AccessTokenRequest(account);
        User user = new User(account, password);
        AccessTokenResponse expectedResponse = AccessTokenResponse.generateTokenOf("testToken", 100000L);

        // when
        when(userRepository.findByApiKey(any())).thenReturn(Optional.of(user));
        when(jwtTokenProvider.generateJwtToken(any())).thenReturn(expectedResponse);
        AccessTokenResponse actualResponse = authenticationService.getAccessToken(apiKey, request);

        // then
        assertNotNull(actualResponse);
        assertEquals(actualResponse.accessToken(), expectedResponse.accessToken());
    }
}