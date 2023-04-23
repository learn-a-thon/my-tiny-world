package com.maetdori.ottention.search.adapter.web.dto.tv;

import com.maetdori.ottention.search.adapter.web.dto.SearchRequest;
import lombok.Getter;

@Getter
public class SearchTvRequest extends SearchRequest {
    private String firstAirDateYear;

    public SearchTvRequest(String page, String includeAdult, String keyword, String firstAirDateYear) {
        super(page, includeAdult, keyword);
        this.firstAirDateYear = firstAirDateYear;
    }
}
