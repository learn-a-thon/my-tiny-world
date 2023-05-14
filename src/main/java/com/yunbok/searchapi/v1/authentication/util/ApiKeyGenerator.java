package com.yunbok.searchapi.v1.authentication.util;

import com.yunbok.searchapi.v1.authentication.application.ApikeyService;
import com.yunbok.searchapi.v1.authentication.exception.ApiAuthenticationException;
import com.yunbok.searchapi.v1.common.define.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class ApiKeyGenerator {

    private final ApikeyService apikeyService;
    private static final int API_KEY_LENGTH = 32;
    private static final String HASH_ALGORITHM = "SHA-256";

    public String generateApiKey() {
        SecureRandom random = new SecureRandom();
        byte[] apiKeyBytes = new byte[API_KEY_LENGTH];
        random.nextBytes(apiKeyBytes);
        String apiKey = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(apiKeyBytes);

        return ensureUniqueApiKey(apiKey);
    }

    public String getHashedApiKey(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hashBytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02x", b));
            }
            return hashBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ApiAuthenticationException(ResponseCode.SERVER_ERROR);
        }
    }

    public String ensureUniqueApiKey(String key) {
        while (apikeyService.isExists(getHashedApiKey(key))) {
            byte[] apiKeyBytes = new byte[API_KEY_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(apiKeyBytes);
            key = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(apiKeyBytes);
        }
        return key;
    }
}
