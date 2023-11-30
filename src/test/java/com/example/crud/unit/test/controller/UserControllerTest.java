package com.example.crud.unit.test.controller;

import com.example.crud.controller.UserController;
import com.example.crud.persistence.entity.BookEntity;
import com.example.crud.persistence.entity.UserBookEntity;
import com.example.crud.persistence.entity.UserEntity;
import com.example.crud.persistence.repos.BookRepository;
import com.example.crud.persistence.repos.UserBookRepository;
import com.example.crud.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserBookRepository userBookRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        List<UserEntity> mockUsers = new ArrayList<>();
        mockUsers.add(new UserEntity(1L, "name 1", "surname 1","email 1"));
        mockUsers.add(new UserEntity(2L, "name 2", "surname 2","email 2"));

        when(userService.getAllUsers()).thenReturn(mockUsers);

        List<UserEntity> result = userController.getAllUsers();

        assertEquals(2, result.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        UserEntity mockUser = new UserEntity(userId, "name 1", "surname 1","email 1");

        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));

        Optional<UserEntity> result = userController.getUserById(userId);

        assertEquals(mockUser, result.orElse(null));
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testCreateUser() {
        UserEntity userToCreate = new UserEntity(1L, "name 1", "surname 1","email 1");

        when(userService.createUser(userToCreate)).thenReturn(userToCreate);

        UserEntity result = userController.createUser(userToCreate);

        assertEquals(userToCreate, result);
        verify(userService, times(1)).createUser(userToCreate);
    }
    @Test
    public void testUpdateUser() {
        Long userId = 1L;

        UserEntity updatedUser = new UserEntity(userId, "name 1", "surname 1","email 1");

        when(userService.updateUser(userId, updatedUser)).thenReturn(updatedUser);

        UserEntity result = userController.updateUser(userId, updatedUser);

        assertEquals(updatedUser, result);
        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    @Test
    public void testDeleteUser() {
        Long userIdToDelete = 1L;

        userController.deleteUser(userIdToDelete);

        verify(userService, times(1)).deleteUser(userIdToDelete);
    }

    @Test
    public void testGetBooksByUserId() {
       Long userId = 1L;
        List<UserBookEntity> userBookEntities = new ArrayList<>();
        userBookEntities.add(new UserBookEntity(1L,1L,2l));
        userBookEntities.add(new UserBookEntity(2L,2L,3l));

        when(userBookRepository.findByUserId(userId)).thenReturn(userBookEntities);

        List<Long> bookIds = userBookEntities.stream()
                .map(UserBookEntity::getLibroId)
                .collect(Collectors.toList());

        List<BookEntity> mockBooks = new ArrayList<>();
        mockBooks.add(new BookEntity(1L, "Book 1", "Description 1"));
        mockBooks.add(new BookEntity(2L, "Book 2", "Description 2"));

        when(bookRepository.findAllById(bookIds)).thenReturn(mockBooks);

        ResponseEntity<List<BookEntity>> response = userController.getBooksByUserId(userId);

        assertEquals(2, response.getBody().size());
        verify(userBookRepository, times(1)).findByUserId(userId);
        verify(bookRepository, times(1)).findAllById(bookIds);
    }
}