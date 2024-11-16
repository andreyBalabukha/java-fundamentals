package com.example.crud_mp.dto.response;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoResponseTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        UUID id = UUID.randomUUID();
        String username = "testUser";
        String email = "test@example.com";
        String role = "USER";

        // Act
        UserDtoResponse userDtoResponse = new UserDtoResponse(id, username, email, role);

        // Assert
        assertEquals(id, userDtoResponse.getId());
        assertEquals(username, userDtoResponse.getUsername());
        assertEquals(email, userDtoResponse.getEmail());
        assertEquals(role, userDtoResponse.getRole());
    }

    @Test
    void testSetters() {
        // Arrange
        UserDtoResponse userDtoResponse = new UserDtoResponse(null, "", "", "");
        UUID newId = UUID.randomUUID();
        String newUsername = "newUser";
        String newEmail = "new@example.com";
        String newRole = "ADMIN";

        // Act
        userDtoResponse.setId(newId);
        userDtoResponse.setUsername(newUsername);
        userDtoResponse.setEmail(newEmail);
        userDtoResponse.setRole(newRole);

        // Assert
        assertEquals(newId, userDtoResponse.getId());
        assertEquals(newUsername, userDtoResponse.getUsername());
        assertEquals(newEmail, userDtoResponse.getEmail());
        assertEquals(newRole, userDtoResponse.getRole());
    }
}