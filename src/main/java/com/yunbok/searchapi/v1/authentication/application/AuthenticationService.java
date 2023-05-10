package com.yunbok.searchapi.v1.authentication.application;

import com.yunbok.searchapi.v1.authentication.domain.ApiKey;
import com.yunbok.searchapi.v1.authentication.domain.vo.JwtToken;
import com.yunbok.searchapi.v1.authentication.presentation.request.AccessTokenRequest;
import com.yunbok.searchapi.v1.authentication.presentation.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.presentation.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.presentation.response.ApiKeyResponse;
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

        return convertApikeyToResponse(user.getApiKey());
    }

    public AccessTokenResponse getAccessToken(String key, AccessTokenRequest request) {
        User user = userRepository.findByApiKey(key)
                .orElseThrow(() -> new ApiAuthenticationException("invalid api key", ResponseCode.UNAUTHORIZED));

        if(user.getAccount().equals(request.account())) {
            throw new ApiAuthenticationException("invalid account", ResponseCode.UNAUTHORIZED);
        }
        JwtToken jwtToken = jwtTokenProvider.generateJwtToken(request.account());

        return convertJwtTokenToResponse(jwtToken);
    }

    private ApiKeyResponse convertApikeyToResponse(ApiKey apiKey) {
        return new ApiKeyResponse(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMessage(),
                apiKey.getKey());
    }

    private AccessTokenResponse convertJwtTokenToResponse(JwtToken jwtToken) {
        return new AccessTokenResponse(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMessage(),
                jwtToken.accessToken(),
                jwtToken.expiredTime(),
                "Bearer");
    }
}
