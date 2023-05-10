package com.yunbok.searchapi.v1.authentication.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiKey {

    @Column(unique = true)
    private String key;

    private LocalDateTime publishDate;

    public ApiKey(String key, LocalDateTime publishDate) {
        this.key = key;
        this.publishDate = publishDate;
    }

    public static ApiKey save(String key) {
        return new ApiKey(key, LocalDateTime.now());
    }
}
