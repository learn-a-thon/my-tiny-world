package com.yunbok.searchapi.v1.authentication.domain;

import com.yunbok.searchapi.v1.authentication.infrastructure.UserRepository;
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

    public void generateApiKey(String key, UserRepository userRepository) {
        this.apiKey = ApiKey.save(key);
        userRepository.save(this);
    }
}
