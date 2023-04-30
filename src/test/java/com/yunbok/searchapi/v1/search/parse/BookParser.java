package com.yunbok.searchapi.v1.search.parse;

import com.yunbok.searchapi.v1.search.entity.Book;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BookParser {

    public static List<Book> parse() {
        String csvFile = "/Users/iyunbog/book.csv";
        String line = "";
        String csvSplitBy = ",";
        ArrayList<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] bookInfo = line.split(csvSplitBy);

                Book book = new Book(bookInfo[0], bookInfo[1], bookInfo[2], bookInfo[3],
                        bookInfo[4], bookInfo[5], bookInfo[6], bookInfo[7]);

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}
