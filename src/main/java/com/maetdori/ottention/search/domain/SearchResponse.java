package com.maetdori.ottention.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse {
    @JsonProperty("page")
    private int page;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonProperty("total_pages")
    private int totalPages;
}
