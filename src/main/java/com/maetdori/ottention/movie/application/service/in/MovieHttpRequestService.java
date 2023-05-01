package com.maetdori.ottention.movie.application.service.in;

import com.maetdori.ottention.movie.adapter.web.dto.MovieQueryParams;
import com.maetdori.ottention.movie.application.port.in.MovieHttpRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class MovieHttpRequestService implements MovieHttpRequestUseCase {
    private static final String MOVIE_URI = "/movie";

    private final WebClient webClient;

    @Override
    public WebClient.ResponseSpec httpGet(String uri, MovieQueryParams queryParams) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(MOVIE_URI + uri)
                        .queryParams(queryParams.getParams())
                        .build())
                .retrieve();
    }
}
