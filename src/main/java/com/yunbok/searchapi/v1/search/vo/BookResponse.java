package com.yunbok.searchapi.v1.search.vo;

import com.yunbok.searchapi.v1.search.domain.Book;

public record BookResponse(
        String library,
        String location,
        String registrationNumber,
        String title,
        String author,
        String publisher,
        String publicationYear,
        String callNumber
) {
    public static BookResponse from(Book book) {
        return new BookResponse(
                book.getLibrary(),
                book.getLocation(),
                book.getRegistrationNumber(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationYear(),
                book.getCallNumber()
        );
    }
}
