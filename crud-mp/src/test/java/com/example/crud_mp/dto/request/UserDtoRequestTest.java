package com.example.crud_mp.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoRequestTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String email = "test@example.com";
        String role = "USER";
        String password = "password";

        // Act
        UserDtoRequest userDtoRequest = new UserDtoRequest(username, email, role, password);

        // Assert
        assertEquals(username, userDtoRequest.getUsername());
        assertEquals(email, userDtoRequest.getEmail());
        assertEquals(role, userDtoRequest.getRole());
        assertEquals(password, userDtoRequest.getPassword());
    }

    @Test
    void testSetters() {
        // Arrange
        UserDtoRequest userDtoRequest = new UserDtoRequest("", "", "", "");
        String newUsername = "newUser";
        String newEmail = "new@example.com";
        String newRole = "ADMIN";
        String newPassword = "newPassword";

        // Act
        userDtoRequest.setUsername(newUsername);
        userDtoRequest.setEmail(newEmail);
        userDtoRequest.setRole(newRole);
        userDtoRequest.setPassword(newPassword);

        // Assert
        assertEquals(newUsername, userDtoRequest.getUsername());
        assertEquals(newEmail, userDtoRequest.getEmail());
        assertEquals(newRole, userDtoRequest.getRole());
        assertEquals(newPassword, userDtoRequest.getPassword());
    }
}