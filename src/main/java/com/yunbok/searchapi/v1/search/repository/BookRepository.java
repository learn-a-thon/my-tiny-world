package com.yunbok.searchapi.v1.search.repository;

import com.yunbok.searchapi.v1.search.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);
}
