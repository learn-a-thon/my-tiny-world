package com.yunbok.searchapi.v1.authentication.domain;

import com.yunbok.searchapi.v1.authentication.vo.response.AccessTokenResponse;
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

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
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

    public AccessTokenResponse generateAccessToken(String account, JwtTokenProvider jwtTokenProvider) {
        if(!this.account.equals(account)) {
            throw new ApiAuthenticationException("invalid account", ResponseCode.UNAUTHORIZED);
        }

        return jwtTokenProvider.generateJwtToken(this.account);
    }
}
