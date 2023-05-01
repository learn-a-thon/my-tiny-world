package com.maetdori.ottention.movie.adapter.web.dto;

import org.springframework.core.env.Environment;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class MovieQueryParams {
    private MultiValueMap<String, String> params;

    public MovieQueryParams(Environment env) {
        params = new LinkedMultiValueMap<>();
        params.add("api_key", env.getProperty("tmdb.api-key"));
    }

    public MultiValueMap<String, String> getParams() {
        return this.params;
    }
}
