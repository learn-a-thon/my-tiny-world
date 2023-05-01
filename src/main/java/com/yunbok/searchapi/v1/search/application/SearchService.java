package com.yunbok.searchapi.v1.search.application;

import com.yunbok.searchapi.v1.search.vo.BookResponse;
import com.yunbok.searchapi.v1.search.vo.SearchRequest;
import com.yunbok.searchapi.v1.search.infrastructure.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final BookRepository bookRepository;

    public List<BookResponse> search(SearchRequest searchRequest) {
        return bookRepository.findByTitleContainingIgnoreCase(searchRequest.query()).stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }
}
