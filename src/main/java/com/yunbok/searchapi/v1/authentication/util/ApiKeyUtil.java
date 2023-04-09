package com.yunbok.searchapi.v1.authentication.util;

import com.yunbok.searchapi.v1.authentication.exception.AuthenticationException;
import com.yunbok.searchapi.v1.common.define.ResponseCode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyUtil {

    private static final int API_KEY_LENGTH = 32;
    private static final String HASH_ALGORITHM = "SHA-256";

    public static String generateApiKey() {
        SecureRandom random = new SecureRandom();
        byte[] apiKeyBytes = new byte[API_KEY_LENGTH];
        random.nextBytes(apiKeyBytes);
        return Base64.getEncoder()
                .encodeToString(apiKeyBytes);
    }
    public static String getHashedApiKey(String apiKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hashBytes = digest.digest(apiKey.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02x", b));
            }
            return hashBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationException(ResponseCode.SERVER_ERROR);
        }
    }
}
