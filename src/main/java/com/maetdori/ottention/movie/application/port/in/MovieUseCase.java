package com.maetdori.ottention.movie.application.port.in;

import com.maetdori.ottention.movie.adapter.web.dto.WatchProviderResponse;

public interface MovieUseCase {
    WatchProviderResponse getWatchProviders(Integer movieId);
}
