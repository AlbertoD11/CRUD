package com.example.crud.service;


import com.example.crud.persistence.entity.UserEntity;
import com.example.crud.persistence.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public List<UserEntity> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public Optional<UserEntity> getUserById(long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error while fetching user by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching user by ID");
        }
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            LOGGER.error("Error while creating user: {}", e.getMessage());
            throw new RuntimeException("Error creating user");
        }
    }

    @Override
    public UserEntity updateUser(long id, UserEntity updatedUser) {
        try {
            UserEntity existingUser = userRepository.findById(id).orElse(null);
            if (existingUser != null) {
                existingUser.setName(updatedUser.getName());
                existingUser.setSurname(updatedUser.getSurname());
                existingUser.setEmail(updatedUser.getEmail());

                return userRepository.save(existingUser);
            }
            throw new RuntimeException("User not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating user: {}", e.getMessage());
            throw new RuntimeException("Error updating user");
        }
    }

    @Override
    public HashMap<String, String> deleteUser(long id) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "User deleted succesfully!");
            userRepository.deleteById(id);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting user: {}", e.getMessage());
            throw new RuntimeException("Error deleting user");
        }
    }

}

