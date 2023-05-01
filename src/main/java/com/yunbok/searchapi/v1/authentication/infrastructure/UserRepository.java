package com.yunbok.searchapi.v1.authentication.infrastructure;

import com.yunbok.searchapi.v1.authentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountAndPassword(String account, String password);

    @Query("SELECT u FROM User u WHERE u.apiKey.key = :key")
    Optional<User> findByApiKey(@Param("key") String key);
}
