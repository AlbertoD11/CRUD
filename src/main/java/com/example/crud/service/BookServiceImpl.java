package com.example.crud.service;

import com.example.crud.persistence.entity.BookEntity;
import com.example.crud.persistence.repos.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<BookEntity> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all books: {}", e.getMessage());
            throw new RuntimeException("Error fetching books");
        }
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        try {
            return bookRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error while fetching book by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching book by ID");
        }
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            LOGGER.error("Error while creating book: {}", e.getMessage());
            throw new RuntimeException("Error creating book");
        }
    }


    @Override
    public BookEntity updateBook(Long id, BookEntity updatedBook) {
        try {
            BookEntity existingBook = bookRepository.findById(id).orElse(null);
            if (existingBook != null) {
                existingBook.setId(updatedBook.getId());
                existingBook.setTitulo(updatedBook.getTitulo());
                existingBook.setDescripcion(updatedBook.getDescripcion());

                return bookRepository.save(existingBook);
            }
            throw new RuntimeException("Book not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating Book: {}", e.getMessage());
            throw new RuntimeException("Error updating Book");
        }
    }

    @Override
    public HashMap<String, String> deleteBook(Long id) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Book deleted succesfully!");
            bookRepository.deleteById(id);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting book: {}", e.getMessage());
            throw new RuntimeException("Error deleting book");
        }
    }

}