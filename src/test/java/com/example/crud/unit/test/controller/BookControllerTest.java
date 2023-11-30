package com.example.crud.unit.test.controller;

import com.example.crud.controller.BookController;
import com.example.crud.persistence.entity.BookEntity;
import com.example.crud.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetAllBooks() {
        List<BookEntity> mockBooks = new ArrayList<>();
        mockBooks.add(new BookEntity(1L, "Book 1", "Description 1"));
        mockBooks.add(new BookEntity(2L, "Book 2", "Description 2"));

        when(bookService.getAllBooks()).thenReturn(mockBooks);

        List<BookEntity> result = bookController.getAllBooks();

        assertEquals(2, result.size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetBookById() {
        Long bookId = 1L;
        BookEntity mockBook = new BookEntity(bookId, "Book 1", "Description 1");

        when(bookService.getBookById(bookId)).thenReturn(Optional.of(mockBook));

        Optional<BookEntity> result = bookController.getBookById(bookId);

        assertEquals(mockBook, result.orElse(null));
        verify(bookService, times(1)).getBookById(bookId);
    }

    @Test
    public void testCreateBook() {
        BookEntity bookToCreate = new BookEntity(1L, "Book 1", "Description 1");

        when(bookService.createBook(bookToCreate)).thenReturn(bookToCreate);

        BookEntity result = bookController.createBook(bookToCreate);

        assertEquals(bookToCreate, result);
        verify(bookService, times(1)).createBook(bookToCreate);
    }

    @Test
    public void testUpdateBook() {
        Long bookId = 1L;
        BookEntity updatedBook = new BookEntity(bookId, "Updated Book", "Updated Description");

        when(bookService.updateBook(bookId, updatedBook)).thenReturn(updatedBook);

        BookEntity result = bookController.updateBook(bookId, updatedBook);

        assertEquals(updatedBook, result);
        verify(bookService, times(1)).updateBook(bookId, updatedBook);
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;

        bookController.deleteBook(bookId);

        verify(bookService, times(1)).deleteBook(bookId);
    }
}

