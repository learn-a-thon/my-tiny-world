package com.yunbok.searchapi.v1.authentication.controller;

import com.yunbok.searchapi.v1.authentication.dto.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.dto.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.service.AuthenticationService;
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
     * @return
     */
    @PostMapping("/api-key")
    public ResponseEntity<ApiKeyResponse> getApiKey(@RequestBody @Valid ApiKeyRequest apiKeyRequest) {
        return ResponseEntity.ok(authenticationService.getApiKey(apiKeyRequest));
    }
}
