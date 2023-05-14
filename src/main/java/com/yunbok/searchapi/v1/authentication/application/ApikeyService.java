package com.yunbok.searchapi.v1.authentication.application;

import com.yunbok.searchapi.v1.authentication.domain.ApiKey;
import com.yunbok.searchapi.v1.authentication.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApikeyService {

    private final UserRepository userRepository;

    public boolean isExists(String key) {
        return userRepository.findByApiKeyKeyString(key).isPresent();
    }
}
