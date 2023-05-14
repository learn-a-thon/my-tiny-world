package com.yunbok.searchapi.v1.search.application;

import com.yunbok.searchapi.v1.search.vo.BookResponse;
import com.yunbok.searchapi.v1.search.vo.SearchRequest;
import com.yunbok.searchapi.v1.search.infrastructure.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final BookRepository bookRepository;

    public List<BookResponse> search(SearchRequest searchRequest) {
        Pageable pageable = PageRequest.of(searchRequest.page(), searchRequest.pageSize(), Sort.by(searchRequest.sort()));

        return bookRepository.findByTitleContainingIgnoreCase(searchRequest.query(), pageable).stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }
}
