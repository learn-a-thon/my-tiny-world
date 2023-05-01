package com.maetdori.ottention.search.adapter.web.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maetdori.ottention.search.adapter.web.dto.SearchResponse;
import com.maetdori.ottention.search.domain.Movie;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class SearchMovieResponse extends SearchResponse {
    @JsonProperty("results")
    private final List<Movie> results = new ArrayList<>();
}
