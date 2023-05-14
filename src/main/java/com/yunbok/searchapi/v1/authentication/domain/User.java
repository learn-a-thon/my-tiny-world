package com.yunbok.searchapi.v1.authentication.domain;

import com.yunbok.searchapi.v1.authentication.domain.vo.JwtToken;
import com.yunbok.searchapi.v1.authentication.presentation.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.exception.ApiAuthenticationException;
import com.yunbok.searchapi.v1.authentication.infrastructure.ApiKeyRepository;
import com.yunbok.searchapi.v1.authentication.util.JwtTokenProvider;
import com.yunbok.searchapi.v1.common.define.ResponseCode;
import com.yunbok.searchapi.v1.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    @Embedded
    private ApiKey apiKey;

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void generateApiKey(String key, ApiKeyRepository apiKeyRepository) {
        ApiKey apiKey = ApiKey.save(key);
        apiKeyRepository.save(apiKey);
        this.apiKey = apiKey;
    }
}
