package com.yunbok.searchapi.v1.authentication.repository;

import com.yunbok.searchapi.v1.authentication.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    boolean existsByApiKey(String apiKey);


    @Query("SELECT a FROM ApiKey a JOIN FETCH a.user WHERE a.apiKey = :apiKey")
    Optional<ApiKey> findByApiKey(@Param("apiKey") String apiKey);
}
