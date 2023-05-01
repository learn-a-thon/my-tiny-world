package com.yunbok.searchapi.v1.authentication.presentation;

import com.yunbok.searchapi.v1.authentication.vo.request.AccessTokenRequest;
import com.yunbok.searchapi.v1.authentication.vo.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.vo.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.vo.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.application.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1/authentication")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Api Key 발급
     * @param apiKeyRequest
     */
    @PostMapping("/api-key")
    public ResponseEntity<ApiKeyResponse> getApiKey(@RequestBody @Valid ApiKeyRequest apiKeyRequest) {
        return ResponseEntity.ok(authenticationService.getApiKey(apiKeyRequest));
    }

    /**
     * Access Token 발급
     * @param apiKey
     * @param accessTokenRequest
     */
    @PostMapping("/access-token")
    public ResponseEntity<AccessTokenResponse> getAccessToken(@RequestHeader("Authorization") String apiKey,
                                                              @RequestBody @Valid AccessTokenRequest accessTokenRequest) {
        return ResponseEntity.ok(
                authenticationService.getAccessToken(apiKey, accessTokenRequest));
    }
}
