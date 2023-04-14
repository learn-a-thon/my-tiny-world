package com.yunbok.searchapi.v1.authentication.repository;

import com.yunbok.searchapi.v1.authentication.entity.ApiKey;
import com.yunbok.searchapi.v1.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    boolean existsByApiKey(String apiKey);
}
