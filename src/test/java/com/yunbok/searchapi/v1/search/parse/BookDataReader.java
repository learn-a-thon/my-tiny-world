package com.yunbok.searchapi.v1.search.parse;

import com.yunbok.searchapi.v1.search.domain.Book;
import com.yunbok.searchapi.v1.search.infrastructure.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-local.yml"})
public class BookDataReader {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 1회성 코드
     * 검색 api에 활용하기 위한 데이터를 파싱하여 저장한다.
     */
    @Test
    public void parseAndSaveBooks() {
        List<Book> books = BookParser.parse();
        bookRepository.saveAll(books);
    }
}
