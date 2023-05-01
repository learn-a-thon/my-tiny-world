package com.maetdori.ottention.search.adapter.web.dto.movie;

import com.maetdori.ottention.search.adapter.web.dto.SearchRequest;
import lombok.Getter;

@Getter
public class SearchMovieRequest extends SearchRequest {
    private String year;
    private String primaryReleaseYear;

    public SearchMovieRequest(String page, String includeAdult, String keyword, String year, String primaryReleaseYear) {
        super(page, includeAdult, keyword);
        this.year = year;
        this.primaryReleaseYear = primaryReleaseYear;
    }
}
