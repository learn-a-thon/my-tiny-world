package com.maetdori.ottention.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Movies extends SearchResponse {
    @JsonProperty("results")
    private final List<Movie> results = new ArrayList<>();
}
