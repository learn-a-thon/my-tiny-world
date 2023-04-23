package com.maetdori.ottention.search.application.port.in;

import com.maetdori.ottention.search.adapter.web.dto.movie.SearchMovieRequest;
import com.maetdori.ottention.search.adapter.web.dto.movie.SearchMovieResponse;
import com.maetdori.ottention.search.adapter.web.dto.multi.SearchMultiRequest;
import com.maetdori.ottention.search.adapter.web.dto.multi.SearchMultiResponse;
import com.maetdori.ottention.search.adapter.web.dto.tv.SearchTvRequest;
import com.maetdori.ottention.search.adapter.web.dto.tv.SearchTvResponse;

public interface SearchUseCase {

    SearchMultiResponse searchMulti(SearchMultiRequest searchMultiRequest);

    SearchMovieResponse searchMovie(SearchMovieRequest searchMovieRequest);

    SearchTvResponse searchTv(SearchTvRequest searchTvRequest);
}
