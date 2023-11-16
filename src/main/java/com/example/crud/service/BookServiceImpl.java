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

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        try {
            return bookRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error while fetching user by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching user by ID");
        }
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            LOGGER.error("Error while creating user: {}", e.getMessage());
            throw new RuntimeException("Error creating user");
        }
    }

    @Override
    public BookEntity updateBook(Long id, BookEntity updatedBook) {
        try {
            BookEntity existingUser = bookRepository.findById(id).orElse(null);
            if (existingUser != null) {
                existingUser.setId(updatedBook.getId());
                existingUser.setTitulo(updatedBook.getTitulo());
                existingUser.setDescripcion(updatedBook.getDescripcion());

                return bookRepository.save(existingUser);
            }
            throw new RuntimeException("User not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating user: {}", e.getMessage());
            throw new RuntimeException("Error updating user");
        }
    }

    @Override
    public HashMap<String, String> deleteBook(Long id) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "User deleted succesfully!");
            bookRepository.deleteById(id);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting user: {}", e.getMessage());
            throw new RuntimeException("Error deleting user");
        }
    }

}