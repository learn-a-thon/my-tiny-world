package com.maetdori.ottention.search.application.port.in;

import com.maetdori.ottention.search.adapter.web.dto.SearchMovieRequest;
import com.maetdori.ottention.search.adapter.web.dto.SearchMovieResponse;

public interface SearchUseCase {
    SearchMovieResponse searchMovies(SearchMovieRequest searchMovieRequest);
}
