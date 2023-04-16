package com.yunbok.searchapi.v1.authentication.entity;

import com.yunbok.searchapi.v1.common.entity.BaseEntity;
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

    public User(Long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }
}
