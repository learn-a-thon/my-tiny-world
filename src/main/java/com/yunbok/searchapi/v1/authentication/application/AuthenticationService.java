package com.yunbok.searchapi.v1.authentication.application;

import com.yunbok.searchapi.v1.authentication.vo.request.AccessTokenRequest;
import com.yunbok.searchapi.v1.authentication.vo.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.vo.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.vo.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.exception.ApiAuthenticationException;
import com.yunbok.searchapi.v1.authentication.infrastructure.ApiKeyRepository;
import com.yunbok.searchapi.v1.authentication.util.ApiKeyGenerator;
import com.yunbok.searchapi.v1.authentication.util.JwtTokenProvider;
import com.yunbok.searchapi.v1.common.define.ResponseCode;
import com.yunbok.searchapi.v1.authentication.domain.User;
import com.yunbok.searchapi.v1.common.exception.ApiClientException;
import com.yunbok.searchapi.v1.authentication.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final ApiKeyGenerator apiKeyGenerator;
    private final UserRepository userRepository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyResponse getApiKey(ApiKeyRequest request) {
        User user = userRepository.findByAccountAndPassword(
                request.account(),
                request.password()).orElseThrow(() -> new ApiClientException("invalid user", ResponseCode.INVALID_REQUEST));
        user.generateApiKey(apiKeyGenerator.generateApiKey(), apiKeyRepository);

        return ApiKeyResponse.successOf(user.getApiKey().getKey());
    }

    public AccessTokenResponse getAccessToken(String key, AccessTokenRequest request) {
        User user = userRepository.findByApiKey(key)
                .orElseThrow(() -> new ApiAuthenticationException("invalid api key", ResponseCode.UNAUTHORIZED));

        return user.generateAccessToken(request.account(), jwtTokenProvider);
    }
}
