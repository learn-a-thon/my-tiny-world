package com.maetdori.ottention.movie.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class WatchProviderResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("results")
    private Result results;

    @NoArgsConstructor
    private static class Result {
        @JsonProperty("KR")
        private Info KR;
    }

    @NoArgsConstructor
    private static class Info {
        @JsonProperty("link")
        private String link;
        @JsonProperty("flatrate")
        private List<Provider> flatrate;
        @JsonProperty("rent")
        private List<Provider> rent;
        @JsonProperty("buy")
        private List<Provider> buy;
    }

    @NoArgsConstructor
    private static class Provider {
        @JsonProperty("display_priority")
        private Integer displayPriority;
        @JsonProperty("logo_path")
        private String logoPath;
        @JsonProperty("provider_id")
        private Integer providerId;
        @JsonProperty("provider_name")
        private String providerName;
    }
}
