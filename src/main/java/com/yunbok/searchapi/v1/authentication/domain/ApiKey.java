package com.yunbok.searchapi.v1.authentication.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
public class ApiKey {

    @Column(unique = true)
    private String keyString;

    private LocalDateTime publishDate;

    public ApiKey(String keyString, LocalDateTime publishDate) {
        this.keyString = keyString;
        this.publishDate = publishDate;
    }

    public static ApiKey save(String key) {
        return new ApiKey(key, LocalDateTime.now());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.keyString);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ApiKey apiKey = (ApiKey) obj;
        return Objects.equals(this.keyString, apiKey.getKeyString());
    }
}
