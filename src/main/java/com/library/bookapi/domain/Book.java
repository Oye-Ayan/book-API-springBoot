package com.library.bookapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be under 200 characters")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Author is required")
    private String author;

    @Size(max = 50, message = "Genre must be under 50 characters")
    private String genre;

    @Column(unique = true)
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
            message = "Invalid ISBN format")
    private String isbn;

    @Min(value = 1000, message = "Year must be 1000 or later")
    @Max(value = 2100, message = "Year seems too far in the future")
    private Integer year;

    @DecimalMin(value = "0.0", inclusive = false,
            message = "Price must be greater than 0")
    private Double price;
}