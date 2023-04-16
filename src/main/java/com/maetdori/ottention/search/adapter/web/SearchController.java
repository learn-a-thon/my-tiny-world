package com.maetdori.ottention.search.adapter.web;

import com.maetdori.ottention.search.adapter.web.dto.SearchMovieRequest;
import com.maetdori.ottention.search.application.port.in.SearchUseCase;
import com.maetdori.ottention.search.adapter.web.dto.SearchMovieResponse;
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

    @GetMapping("/movie")
    public SearchMovieResponse searchMovies(@ModelAttribute SearchMovieRequest searchMovieRequest) {
        return searchUseCase.searchMovies(searchMovieRequest);
    }
}
