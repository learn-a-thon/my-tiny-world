package com.yunbok.searchapi.v1.search.infrastructure;

import com.yunbok.searchapi.v1.search.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);
}
