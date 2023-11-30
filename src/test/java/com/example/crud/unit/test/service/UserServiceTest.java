package com.example.crud.unit.test.service;

import com.example.crud.persistence.entity.UserEntity;
import com.example.crud.persistence.repos.UserRepository;
import com.example.crud.service.UserServiceImpl;
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
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void testGetAllUsers() {

        List<UserEntity> mockUser = new ArrayList<>();
        mockUser.add(new UserEntity(1L, "name 1", "surname 1", "email 1"));
        mockUser.add(new UserEntity(2L, "name 2", "surname 2", "email 2"));

        when(userRepository.findAll()).thenReturn(mockUser);

        List<UserEntity> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetUserById() {

        UserEntity mockUser = new UserEntity(1L, "name 1", "surname 1", "email 1");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        Optional<UserEntity> result = userService.getUserById(1L);

        assertEquals(mockUser, result.orElse(null));
    }

    @Test
    public void testCreateUser() {

        UserEntity userToCreate = new UserEntity(1L, "name 1", "surname 1", "email 1");
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userToCreate);

        UserEntity result = userService.createUser(userToCreate);
        assertEquals(userToCreate, result);

    }

    @Test
    public void testUpdateUser() {
        Long existingUserId = 1L;
        UserEntity existingUser = new UserEntity(existingUserId, "name 1", "surname 1", "email 1");
        UserEntity updateUser = new UserEntity(existingUserId, "name 2", "surname 2", "email 2");

        Mockito.when(userRepository.findById(existingUserId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(updateUser);

        UserEntity result = userService.updateUser(existingUserId, updateUser);

        assertEquals(updateUser, result);

    }

    @Test
    public void testDeleteUser() {
        Long userIdToDelete = 1L;

        doNothing().when(userRepository).deleteById(userIdToDelete);

        HashMap<String, String> response = userService.deleteUser(userIdToDelete);

        assertEquals("User deleted succesfully!", response.get("message"));
        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }

}
