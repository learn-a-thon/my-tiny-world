package com.maetdori.ottention.movie.application.service.in;

import com.maetdori.ottention.movie.adapter.web.dto.MovieQueryParams;
import com.maetdori.ottention.movie.adapter.web.dto.WatchProviderResponse;
import com.maetdori.ottention.movie.application.port.in.MovieHttpRequestUseCase;
import com.maetdori.ottention.movie.application.port.in.MovieUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieService implements MovieUseCase {
    private final MovieHttpRequestUseCase movieHttpRequestUseCase;
    private final Environment env;

    @Override
    public WatchProviderResponse getWatchProviders(Integer movieId) {
        MovieQueryParams queryParams = new MovieQueryParams(env);

        return movieHttpRequestUseCase.httpGet("/" + movieId + "/watch/providers", queryParams)
                .bodyToMono(WatchProviderResponse.class)
                .block();
    }
}
