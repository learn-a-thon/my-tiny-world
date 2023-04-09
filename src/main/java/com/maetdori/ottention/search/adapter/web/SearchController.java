package com.maetdori.ottention.search.adapter.web;

import com.maetdori.ottention.search.adapter.web.dto.SearchMovieRequest;
import com.maetdori.ottention.search.application.service.in.SearchService;
import com.maetdori.ottention.search.domain.Movies;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/movie")
    public Movies searchMovies(@ModelAttribute SearchMovieRequest searchMovieRequest) {
        return searchService.searchMovies(searchMovieRequest);
    }
}
