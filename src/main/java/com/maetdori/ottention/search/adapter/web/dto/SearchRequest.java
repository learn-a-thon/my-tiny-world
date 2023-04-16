package com.maetdori.ottention.search.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class SearchRequest {

    @NonNull
    private String page;

    private String includeAdult;

    @NotBlank(message = "검색어를 입력해주세요.")
    private String keyword;

    public SearchRequest(String page, String includeAdult, String keyword) {
        this.page = page;
        this.includeAdult = includeAdult;
        this.keyword = keyword;
    }
}
