package com.yunbok.searchapi.v1.search.controller;

import com.yunbok.searchapi.v1.search.dto.BookResponse;
import com.yunbok.searchapi.v1.search.dto.SearchRequest;
import com.yunbok.searchapi.v1.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/search")
@RestController
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<?> searchWeb(SearchRequest searchRequest) {
        List<BookResponse> result = searchService.search(searchRequest);

        return ResponseEntity.ok(result);
    }
}
