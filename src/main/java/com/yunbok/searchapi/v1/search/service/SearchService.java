package com.yunbok.searchapi.v1.search.service;

import com.yunbok.searchapi.v1.search.dto.BookResponse;
import com.yunbok.searchapi.v1.search.dto.SearchRequest;
import com.yunbok.searchapi.v1.search.entity.Book;
import com.yunbok.searchapi.v1.search.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final BookRepository bookRepository;

    public List<BookResponse> search(SearchRequest searchRequest) {
        return bookRepository.findByTitleContainingIgnoreCase(searchRequest.query()).stream()
                .map(this::convertBookToResponse)
                .collect(Collectors.toList());
    }

    private BookResponse convertBookToResponse(Book book) {
        return new BookResponse(book.getLibrary(), book.getLocation(), book.getRegistrationNumber(),
                book.getTitle(), book.getAuthor(), book.getPublisher(),
                book.getPublicationYear(), book.getCallNumber());
    }
}
