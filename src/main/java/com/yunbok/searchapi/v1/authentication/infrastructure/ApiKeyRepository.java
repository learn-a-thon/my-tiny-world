package com.yunbok.searchapi.v1.authentication.infrastructure;

import com.yunbok.searchapi.v1.authentication.domain.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    boolean existsByApiKey(String apiKey);
}
