package com.yunbok.searchapi.v1.authentication.service;

import com.yunbok.searchapi.v1.authentication.dto.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.dto.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.entity.ApiKey;
import com.yunbok.searchapi.v1.authentication.repository.ApiKeyRepository;
import com.yunbok.searchapi.v1.authentication.util.ApiKeyUtil;
import com.yunbok.searchapi.v1.authentication.entity.User;
import com.yunbok.searchapi.v1.common.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Mock
    private UserRepository userRepository;

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

        when(userRepository.findByAccountAndPassword(account, password)).thenReturn(user);
        when(ApiKeyUtil.generateApiKey()).thenReturn(apiKey);

        ApiKeyRequest request = ApiKeyRequest.requestOf(account, password);

        // when
        ApiKeyResponse response = authenticationService.getApiKey(request);
        verify(apiKeyRepository).save(refEq(ApiKey.save(user, ApiKeyUtil.getHashedApiKey(apiKey))));

        // then
        assertEquals(apiKey, response.getApiKey());
    }
}