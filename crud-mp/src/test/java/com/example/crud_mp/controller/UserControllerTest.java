package com.example.crud_mp.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.crud_mp.dto.request.UserDtoRequest;
import com.example.crud_mp.dto.response.UserDtoResponse;
import com.example.crud_mp.model.User;
import com.example.crud_mp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = List.of(new User());
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testCreateUser() throws Exception {
        UserDtoRequest userDtoRequest = new UserDtoRequest("username", "email@example.com", "USER", "password");
        List<User> users = List.of(new User());
        when(userService.createUser(any(UserDtoRequest.class))).thenReturn(users);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"username\", \"email\": \"email@example.com\", \"role\": \"USER\", \"password\": \"password\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isArray());

        verify(userService, times(1)).createUser(any(UserDtoRequest.class));
    }

    @Test
    void testGetUserById() throws Exception {
        UUID userId = UUID.randomUUID();
        UserDtoResponse userDtoResponse = new UserDtoResponse(userId, "username", "email@example.com", "USER");
        when(userService.getUserById(userId)).thenReturn(userDtoResponse);

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testDeleteUser() throws Exception {
        UUID userId = UUID.randomUUID();

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(userId);
    }
}