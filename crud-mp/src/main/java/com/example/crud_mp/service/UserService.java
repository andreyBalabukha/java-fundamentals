package com.example.crud_mp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crud_mp.CrudMpApplication;
import com.example.crud_mp.dto.request.UserDtoRequest;
import com.example.crud_mp.dto.response.UserDtoResponse;
import com.example.crud_mp.exceptions.UserNotFoundException;
import com.example.crud_mp.model.User;
import com.example.crud_mp.repository.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(CrudMpApplication.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findAllUsers();
            return users;
        } catch (Exception e) {
            logger.error("Error getting all users: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public List<User> createUser(UserDtoRequest user) {
        validateUserDtoRequest(user);
        try {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            User newUser = new User(user.getUsername(), user.getEmail(), user.getRole(), hashedPassword);
            userRepository.save(newUser);
            logger.info("User created: " + newUser.getId());
            return userRepository.findAllUsers();
        } 
        catch (Exception e) {
            logger.error("Error creating user: " + e.getMessage());
            throw e;
        }
    }

    public UserDtoResponse getUserById(UUID id) {
        validateUUID(id);
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                User foundUser = user.get();
                return new UserDtoResponse(foundUser.getId(), foundUser.getUsername(), foundUser.getEmail(), foundUser.getRole());
            } else {
                throw new UserNotFoundException("User with id " + id + " not found.");
            }
        } catch (UserNotFoundException e) {
            logger.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error retrieving user: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void deleteUser(UUID id) {
        validateUUID(id);
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                logger.info("User with id " + id + " has been deleted.");
            } else {
                throw new UserNotFoundException("User with id " + id + " not found.");
            }
        } catch (UserNotFoundException e) {
            logger.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting user: {}", e.getMessage());
            throw e;
        }
    }

    private void validateUserDtoRequest(UserDtoRequest user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    private void validateUUID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
    }
}
