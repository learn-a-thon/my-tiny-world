package com.maetdori.ottention.movie.adapter.web;

import com.maetdori.ottention.movie.adapter.web.dto.WatchProviderResponse;
import com.maetdori.ottention.movie.application.port.in.MovieUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movie")
@RestController
public class MovieController {
    private final MovieUseCase movieUseCase;

    @GetMapping("/{movieId}/watch/providers")
    public WatchProviderResponse getWatchProviders(@PathVariable Integer movieId) {
        return movieUseCase.getWatchProviders(movieId);
    }
}
