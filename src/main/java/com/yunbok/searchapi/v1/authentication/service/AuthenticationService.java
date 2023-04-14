package com.yunbok.searchapi.v1.authentication.service;

import com.yunbok.searchapi.v1.authentication.dto.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.dto.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.entity.ApiKey;
import com.yunbok.searchapi.v1.authentication.repository.ApiKeyRepository;
import com.yunbok.searchapi.v1.authentication.util.ApiKeyGenerator;
import com.yunbok.searchapi.v1.common.define.ResponseCode;
import com.yunbok.searchapi.v1.authentication.entity.User;
import com.yunbok.searchapi.v1.common.exception.ApiClientException;
import com.yunbok.searchapi.v1.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final ApiKeyGenerator apiKeyGenerator;
    private final ApiKeyRepository apiKeyRepository;
    private final UserRepository userRepository;

    public ApiKeyResponse getApiKey(ApiKeyRequest request) {
        User user = userRepository.findByAccountAndPassword(
                request.getAccount(),
                request.getPassword()).orElseThrow(() -> new ApiClientException("invalid user", ResponseCode.INVALID_REQUEST));
        String apiKey = apiKeyGenerator.generateApiKey();
        apiKeyRepository.save(ApiKey.save(user, apiKeyGenerator.getHashedApiKey(apiKey)));

        return ApiKeyResponse.successOf(apiKey);
    }
}
