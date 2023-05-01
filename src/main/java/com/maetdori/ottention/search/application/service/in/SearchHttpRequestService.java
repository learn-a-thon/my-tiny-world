package com.maetdori.ottention.search.application.service.in;

import com.maetdori.ottention.search.adapter.web.dto.SearchQueryParams;
import com.maetdori.ottention.search.application.port.in.SearchHttpRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class SearchHttpRequestService implements SearchHttpRequestUseCase {
    private static final String SEARCH_URI = "/search";

    private final WebClient webClient;

    @Override
    public WebClient.ResponseSpec httpGet(String uri, SearchQueryParams queryParams) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(SEARCH_URI + uri)
                        .queryParams(queryParams.getParams())
                        .build())
                .retrieve();
    }

}
