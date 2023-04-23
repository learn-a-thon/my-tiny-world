package com.yunbok.searchapi.v1.authentication.service;


import com.yunbok.searchapi.v1.authentication.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    public boolean isExistsApiKey(String apiKey) {
        return apiKeyRepository.existsByApiKey(apiKey);
    }
}
