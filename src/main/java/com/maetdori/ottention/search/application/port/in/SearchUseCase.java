package com.maetdori.ottention.search.application.port.in;

import com.maetdori.ottention.search.adapter.web.dto.SearchMovieRequest;
import com.maetdori.ottention.search.domain.Movies;

public interface SearchUseCase {
    Movies searchMovies(SearchMovieRequest searchMovieRequest);
}
