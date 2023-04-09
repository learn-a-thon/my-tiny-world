package com.maetdori.ottention.search.adapter.web.dto;

import lombok.Getter;

@Getter
public class SearchMovieRequest extends SearchRequest {
    private Integer year;
    private Integer primaryReleaseYear;

    public SearchMovieRequest(Integer page, Boolean includeAdult, String keyword, Integer year, Integer primaryReleaseYear) {
        super(page, includeAdult, keyword);
        this.year = year;
        this.primaryReleaseYear = primaryReleaseYear;
    }
}
