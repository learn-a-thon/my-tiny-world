package com.yunbok.searchapi.v1.search.dto;

public record BookResponse(
        String library,
        String location,
        String registrationNumber,
        String title,
        String author,
        String publisher,
        String publicationYear,
        String callNumber
) {}
