package com.maetdori.ottention.search.adapter.web;

import com.maetdori.ottention.search.adapter.web.dto.movie.SearchMovieRequest;
import com.maetdori.ottention.search.adapter.web.dto.movie.SearchMovieResponse;
import com.maetdori.ottention.search.adapter.web.dto.multi.SearchMultiRequest;
import com.maetdori.ottention.search.adapter.web.dto.multi.SearchMultiResponse;
import com.maetdori.ottention.search.adapter.web.dto.tv.SearchTvRequest;
import com.maetdori.ottention.search.adapter.web.dto.tv.SearchTvResponse;
import com.maetdori.ottention.search.application.port.in.SearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchController {
    private final SearchUseCase searchUseCase;

    @GetMapping("/multi")
    public SearchMultiResponse searchMulti(@ModelAttribute SearchMultiRequest searchMultiRequest) {
        return searchUseCase.searchMulti(searchMultiRequest);
    }

    @GetMapping("/movie")
    public SearchMovieResponse searchMovies(@ModelAttribute SearchMovieRequest searchMovieRequest) {
        return searchUseCase.searchMovie(searchMovieRequest);
    }

    @GetMapping("/tv")
    public SearchTvResponse searchTv(@ModelAttribute SearchTvRequest searchTvRequest) {
        return searchUseCase.searchTv(searchTvRequest);
    }
}
