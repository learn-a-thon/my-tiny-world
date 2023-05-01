package com.yunbok.searchapi.v1.authentication.domain;


import com.yunbok.searchapi.v1.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ApiKey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;

    public ApiKey(String key) {
        this.key = key;
    }

    public static ApiKey save(String key) {
        return new ApiKey(key);
    }
}
