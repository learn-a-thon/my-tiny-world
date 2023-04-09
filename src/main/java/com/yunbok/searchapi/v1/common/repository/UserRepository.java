package com.yunbok.searchapi.v1.common.repository;

import com.yunbok.searchapi.v1.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountAndPassword(String account, String password);
}
