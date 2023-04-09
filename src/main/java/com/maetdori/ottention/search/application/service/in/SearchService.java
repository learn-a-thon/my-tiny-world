package com.maetdori.ottention.search.application.service.in;

import com.maetdori.ottention.search.adapter.web.dto.SearchMovieRequest;
import com.maetdori.ottention.search.application.port.in.SearchUseCase;
import com.maetdori.ottention.search.domain.Movies;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    public Movies searchMovies(SearchMovieRequest searchMovieRequest) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(SEARCH_URI + "/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", language)
                        .queryParam("region", region)
                        .queryParam("page", searchMovieRequest.getPage())
                        .queryParam("include_adult", searchMovieRequest.getIncludeAdult())
                        .queryParam("query", searchMovieRequest.getKeyword())
                        .queryParam("year", searchMovieRequest.getYear())
                        .queryParam("primary_release_year", searchMovieRequest.getPrimaryReleaseYear())
                        .build())
                .retrieve()
                .bodyToMono(Movies.class)
                .block();
    }
}
