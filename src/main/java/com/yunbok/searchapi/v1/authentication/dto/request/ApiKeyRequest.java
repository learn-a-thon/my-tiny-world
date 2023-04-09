package com.yunbok.searchapi.v1.authentication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ApiKeyRequest {

    @NotBlank(message = "Account must not be blank")
    private String account;

    @NotBlank(message = "Password must not be blank")
    private String password;

    private ApiKeyRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public static ApiKeyRequest requestOf(String account, String password) {
        return new ApiKeyRequest(account, password);
    }
}
