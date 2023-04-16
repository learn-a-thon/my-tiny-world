package com.yunbok.searchapi.v1.authentication.entity;


import com.yunbok.searchapi.v1.common.entity.BaseEntity;
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

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String apiKey;

    private ApiKey(User user, String apiKey) {
        this.user = user;
        this.apiKey = apiKey;
    }

    public static ApiKey save(User user, String apiKey) {
        return new ApiKey(user, apiKey);
    }
}
