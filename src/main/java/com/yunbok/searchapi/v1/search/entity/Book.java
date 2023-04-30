package com.yunbok.searchapi.v1.search.entity;


import com.yunbok.searchapi.v1.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String library;
    private String location;
    private String registrationNumber;
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String callNumber;

    public Book(String library, String location, String registrationNumber, String title,
                String author, String publisher, String publicationYear, String callNumber) {
        this.library = library;
        this.location = location;
        this.registrationNumber = registrationNumber;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.callNumber = callNumber;
    }
}
