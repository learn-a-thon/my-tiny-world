package com.maetdori.ottention.search.application.port.in;

import com.maetdori.ottention.search.adapter.web.dto.SearchQueryParams;
import org.springframework.web.reactive.function.client.WebClient;

public interface SearchHttpRequestUseCase {

    WebClient.ResponseSpec httpGet(String uri, SearchQueryParams queryParams);
}
