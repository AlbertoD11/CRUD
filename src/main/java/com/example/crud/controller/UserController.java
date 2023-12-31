package com.example.crud.controller;


import com.example.crud.persistence.entity.BookEntity;
import com.example.crud.persistence.entity.UserBookEntity;
import com.example.crud.persistence.entity.UserEntity;
import com.example.crud.persistence.repos.BookRepository;
import com.example.crud.persistence.repos.UserBookRepository;
import com.example.crud.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "Usuarios", description = "crud de usuarios")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    private final BookRepository bookRepository;

    private final UserBookRepository userBookRepository;

    @Autowired
    public UserController(UserServiceImpl userService, BookRepository bookRepository,
                          UserBookRepository userBookRepository) {

        this.userService = userService;
        this.bookRepository = bookRepository;
        this.userBookRepository = userBookRepository;
    }


    @ApiOperation(
            value = "find all users", notes = "method that return all user")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(
            value = "find users by id", notes = "method that return  user by id")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @GetMapping("/{id}")

    public Optional<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @ApiOperation(
            value = "create users", notes = "method that create user")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }


    @ApiOperation(
            value = "Update an existing user", notes = "method that update an existing user")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser) {
        return userService.updateUser(id, updatedUser);
    }


    @ApiOperation(
            value = "delete users", notes = "method that delete user")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @ApiOperation(
            value = "find books by users", notes = "method that find all books by user")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})

    @GetMapping("/{userId}/books")
    public ResponseEntity<List<BookEntity>> getBooksByUserId(@PathVariable Long userId) {
        List<UserBookEntity> userBookEntities = userBookRepository.findByUserId(userId);

        if (userBookEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Long> bookIds = userBookEntities.stream()
                .map(UserBookEntity::getLibroId)
                .collect(Collectors.toList());

        List<BookEntity> books = bookRepository.findAllById(bookIds);

        return ResponseEntity.ok(books);
    }


}
