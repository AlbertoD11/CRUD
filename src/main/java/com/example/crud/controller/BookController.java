package com.example.crud.controller;

import com.example.crud.persistence.entity.BookEntity;
import com.example.crud.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @ApiOperation(
            value = "find all books", notes = "method that return all books")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    @ApiOperation(
            value = "find book by id", notes = "method that return  book by id")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @GetMapping("/{id}")
    public Optional<BookEntity> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @ApiOperation(
            value = "create book", notes = "method that create book")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})


    @PostMapping
    public BookEntity createBook(@RequestBody BookEntity book) {
        return bookService.createBook(book);
    }

    @ApiOperation(
            value = "Update an existing book", notes = "method that update an existing book")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

