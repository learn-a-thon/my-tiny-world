package com.maetdori.ottention.search.application.service.in;

import com.maetdori.ottention.search.adapter.web.dto.SearchMovieRequest;
import com.maetdori.ottention.search.application.port.in.SearchUseCase;
import com.maetdori.ottention.search.adapter.web.dto.SearchMovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class SearchService implements SearchUseCase {
    private static final String SEARCH_URI = "/search";

    private final WebClient webClient;

    @Value("${tmdb.api-key}")
    private String apiKey;

    @Value("${tmdb.language}")
    private String language;

    @Value("${tmdb.region}")
    private String region;

    @Override
    public SearchMovieResponse searchMovies(SearchMovieRequest searchMovieRequest) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("api_key", apiKey);
        params.add("language", language);
        params.add("region", region);
        params.add("page", searchMovieRequest.getPage());
        params.add("include_adult", searchMovieRequest.getIncludeAdult());
        params.add("query", searchMovieRequest.getKeyword());
        params.add("year", searchMovieRequest.getYear());
        params.add("primary_release_year", searchMovieRequest.getPrimaryReleaseYear());

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(SEARCH_URI + "/movie")
                        .queryParams(params)
                        .build())
                .retrieve()
                .bodyToMono(SearchMovieResponse.class)
                .block();
    }
}
