package com.library.bookapi.dto;

import com.library.bookapi.dto.request.BookRequest;
import com.library.bookapi.dto.response.BookResponse;
import com.library.bookapi.model.Book;

public class BookMapper {

    public static Book toEntity(BookRequest req) {
        Book book = new Book();
        book.setTitle(req.getTitle().trim());
        book.setAuthor(req.getAuthor().trim());
        book.setGenre(req.getGenre());
        book.setIsbn(req.getIsbn());
        book.setYear(req.getYear());
        book.setPrice(req.getPrice());
        return book;
    }

    public static void updateEntity(Book book, BookRequest req) {
        book.setTitle(req.getTitle().trim());
        book.setAuthor(req.getAuthor().trim());
        book.setGenre(req.getGenre());
        book.setIsbn(req.getIsbn());
        book.setYear(req.getYear());
        book.setPrice(req.getPrice());
    }

    public static BookResponse toResponse(Book book) {
        BookResponse res = new BookResponse();
        res.setId(book.getId());
        res.setTitle(book.getTitle());
        res.setAuthor(book.getAuthor());
        res.setGenre(book.getGenre());
        res.setIsbn(book.getIsbn());
        res.setYear(book.getYear());
        res.setPrice(book.getPrice());
        if (book.getTitle() != null && book.getYear() != null)
            res.setDisplayLabel(book.getTitle() + " (" + book.getYear() + ")");
        return res;
    }
}