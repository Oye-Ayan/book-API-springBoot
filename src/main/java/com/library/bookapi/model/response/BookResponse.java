package com.library.bookapi.model.response;
import lombok.Data;

@Data
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private Integer year;
    private Double price;

    // Example computed field — derived, not stored in DB
    private String displayLabel;  // e.g. "Clean Code (2008)"
}