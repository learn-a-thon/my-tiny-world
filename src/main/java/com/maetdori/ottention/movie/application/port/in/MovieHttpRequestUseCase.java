package com.maetdori.ottention.movie.application.port.in;

import com.maetdori.ottention.movie.adapter.web.dto.MovieQueryParams;
import org.springframework.web.reactive.function.client.WebClient;

public interface MovieHttpRequestUseCase {

    WebClient.ResponseSpec httpGet(String uri, MovieQueryParams queryParams);
}
