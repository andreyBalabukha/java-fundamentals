package com.example.crud_mp.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String email = "test@example.com";
        String role = "USER";
        String password = "password";

        // Act
        User user = new User(username, email, role, password);

        // Assert
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testSetters() {
        // Arrange
        User user = new User();
        UUID id = UUID.randomUUID();
        String username = "newUser";
        String email = "new@example.com";
        String role = "ADMIN";
        String password = "newPassword";

        // Act
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testPrePersist() {
        // Arrange
        User user = new User();

        // Act
        user.prePersist();

        // Assert
        assertNotNull(user.getId());
    }

    @Test
    void testPrePersistWithExistingId() {
        // Arrange
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);

        // Act
        user.prePersist();

        // Assert
        assertEquals(id, user.getId());
    }
}