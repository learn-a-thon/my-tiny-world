package com.maetdori.ottention.search.adapter.web.dto;

import org.springframework.core.env.Environment;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class SearchQueryParams {
    private MultiValueMap<String, String> params;

    public SearchQueryParams(Environment env, SearchRequest searchRequest) {
        params = new LinkedMultiValueMap<>();
        params.add("api_key", env.getProperty("tmdb.api-key"));
        params.add("language", env.getProperty("tmdb.language"));
        params.add("region", env.getProperty("tmdb.region"));
        params.add("page", searchRequest.getPage());
        params.add("include_adult", searchRequest.getIncludeAdult());
        params.add("query", searchRequest.getKeyword());
    }

    public void add(String key, String value) {
        this.params.add(key, value);
    }

    public MultiValueMap<String, String> getParams() {
        return this.params;
    }
}
