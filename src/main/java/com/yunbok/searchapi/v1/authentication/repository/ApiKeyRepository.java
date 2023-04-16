package com.yunbok.searchapi.v1.authentication.repository;

import com.yunbok.searchapi.v1.authentication.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
}
