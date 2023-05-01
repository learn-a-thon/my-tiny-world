package com.yunbok.searchapi.v1.search.vo;

public record SearchRequest(
        String query,
        String sort,
        int page,
        int pageSize
) {}

