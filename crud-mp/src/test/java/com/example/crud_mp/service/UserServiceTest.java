package com.example.crud_mp.service;

import com.example.crud_mp.dto.request.UserDtoRequest;
import com.example.crud_mp.dto.response.UserDtoResponse;
import com.example.crud_mp.exceptions.UserNotFoundException;
import com.example.crud_mp.model.User;
import com.example.crud_mp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User());
        when(userRepository.findAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(users, result);
        verify(userRepository, times(1)).findAllUsers();
    }

    @Test
    void testCreateUser() {
        UserDtoRequest userDtoRequest = new UserDtoRequest("username", "email@example.com", "USER", "password");
        User newUser = new User("username", "email@example.com", "USER", "hashedPassword");

        when(passwordEncoder.encode(userDtoRequest.getPassword())).thenReturn("hashedPassword");
        when(userRepository.findAllUsers()).thenReturn(List.of(newUser));

        List<User> result = userService.createUser(userDtoRequest);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "username", "email@example.com", "USER", "password");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDtoResponse result = userService.getUserById(userId);

        assertEquals(userId, result.getId());
        assertEquals("username", result.getUsername());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserByIdNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testDeleteUser() {
        UUID userId = UUID.randomUUID();
        when(userRepository.existsById(userId)).thenReturn(true);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
    }

    @Test
    void testValidateUserDtoRequestThrowsException() {
        UserDtoRequest invalidUser = new UserDtoRequest("", "", "", "");

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(invalidUser));
    }
}