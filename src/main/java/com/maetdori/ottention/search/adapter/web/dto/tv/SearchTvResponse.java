package com.maetdori.ottention.search.adapter.web.dto.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maetdori.ottention.search.adapter.web.dto.SearchResponse;
import com.maetdori.ottention.search.domain.Tv;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class SearchTvResponse extends SearchResponse {
    @JsonProperty("results")
    private final List<Tv> results = new ArrayList<>();

}
