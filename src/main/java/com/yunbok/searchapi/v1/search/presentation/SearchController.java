package com.yunbok.searchapi.v1.search.presentation;

import com.yunbok.searchapi.v1.search.vo.BookResponse;
import com.yunbok.searchapi.v1.search.vo.SearchRequest;
import com.yunbok.searchapi.v1.search.application.SearchService;
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
    public ResponseEntity<List<BookResponse>> searchWeb(SearchRequest searchRequest) {
        List<BookResponse> result = searchService.search(searchRequest);

        return ResponseEntity.ok(result);
    }
}
