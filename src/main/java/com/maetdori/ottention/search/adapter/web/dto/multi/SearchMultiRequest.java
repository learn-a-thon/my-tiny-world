package com.maetdori.ottention.search.adapter.web.dto.multi;

import com.maetdori.ottention.search.adapter.web.dto.SearchRequest;

public class SearchMultiRequest extends SearchRequest {

    public SearchMultiRequest(String page, String includeAdult, String keyword) {
        super(page, includeAdult, keyword);
    }
}
