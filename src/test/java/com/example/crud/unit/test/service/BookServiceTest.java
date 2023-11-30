package com.example.crud.unit.test.service;

import com.example.crud.persistence.entity.BookEntity;
import com.example.crud.persistence.repos.BookRepository;
import com.example.crud.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;


    @Test
    public void testGetAllBooks() {

        List<BookEntity> mockBooks = new ArrayList<>();
        mockBooks.add(new BookEntity(1L, "Libro 1", "Descripción 1"));
        mockBooks.add(new BookEntity(2L, "Libro 2", "Descripción 2"));

        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<BookEntity> result = bookService.getAllBooks();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetBookById() {

        BookEntity mockBook = new BookEntity(1L, "Libro 1", "Descripción 1");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

        Optional<BookEntity> result = bookService.getBookById(1L);

        assertEquals(mockBook, result.orElse(null));
    }

    @Test
    public void testCreateBook() {

        BookEntity bookToCreate = new BookEntity(1L, "Libro 1", "Descripción 1");
        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(bookToCreate);

        BookEntity result = bookService.createBook(bookToCreate);
        assertEquals(bookToCreate, result);

    }

    @Test
    public void testUpdateBook() {
        Long existingBookId = 1L;
        BookEntity existingBook = new BookEntity(existingBookId, "Libro existente", "Descripción del libro existente");
        BookEntity updatedBook = new BookEntity(existingBookId, "Libro actualizado", "Descripción actualizada");

        Mockito.when(bookRepository.findById(existingBookId)).thenReturn(Optional.of(existingBook));
        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(updatedBook);

        BookEntity result = bookService.updateBook(existingBookId, updatedBook);

        assertEquals(updatedBook, result);

    }

    @Test
    public void testDeleteBook() {
        Long bookIdToDelete = 1L;

        doNothing().when(bookRepository).deleteById(bookIdToDelete);

        HashMap<String, String> response = bookService.deleteBook(bookIdToDelete);

        assertEquals("Book deleted succesfully!", response.get("message"));
        verify(bookRepository, times(1)).deleteById(bookIdToDelete);
    }

}

