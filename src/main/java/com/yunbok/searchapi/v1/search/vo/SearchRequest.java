package com.yunbok.searchapi.v1.search.vo;

import com.yunbok.searchapi.v1.common.define.Sort;

public record SearchRequest(
        String query,
        String sort,
        int page,
        int pageSize
) {
    public Sort getSort() {
        try {
            return Sort.valueOf(sort.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Sort.TITLE;
        }
    }
}

