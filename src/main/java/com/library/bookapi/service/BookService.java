package com.library.bookapi.service;

import com.library.bookapi.model.BookMapper;
import com.library.bookapi.model.request.BookRequest;
import com.library.bookapi.model.response.BookResponse;
import com.library.bookapi.domain.Book;
import com.library.bookapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<BookResponse> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toResponse);
    }

    public BookResponse createBook(BookRequest req) {
        Book saved = bookRepository.save(BookMapper.toEntity(req));
        return BookMapper.toResponse(saved);
    }

    public Optional<BookResponse> updateBook(Long id, BookRequest req) {
        return bookRepository.findById(id).map(existing -> {
            BookMapper.updateEntity(existing, req);
            return BookMapper.toResponse(bookRepository.save(existing));
        });
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}