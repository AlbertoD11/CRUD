package com.example.crud.service;

import com.example.crud.persistence.entity.BookEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookEntity> getAllBooks();

    Optional<BookEntity> getBookById(Long id);

    BookEntity createBook(BookEntity book);

    BookEntity updateBook(Long id, BookEntity updatedBook);

    HashMap<String, String> deleteBook(Long id);
}
