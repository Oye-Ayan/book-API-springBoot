package com.library.bookapi.repository;

import com.library.bookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Spring generates the SQL query from the method name!
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByTitleContainingIgnoreCase(String title);
}