package com.example.crud.service;

import com.example.crud.persistence.entity.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserById(long id);

    UserEntity createUser(UserEntity user);

    UserEntity updateUser(long id, UserEntity updatedUser);

    HashMap<String, String> deleteUser(long id);

}