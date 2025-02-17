package com.example.crud_mp.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud_mp.CrudMpApplication;
import com.example.crud_mp.dto.request.UserDtoRequest;
import com.example.crud_mp.dto.response.UserDtoResponse;
import com.example.crud_mp.model.User;
import com.example.crud_mp.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    
    Logger logger = LoggerFactory.getLogger(CrudMpApplication.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<User>> createUser(@RequestBody UserDtoRequest user) {
        List<User> users =  userService.createUser(user);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable UUID id) {
        logger.info("User requested: " + id);
        UserDtoResponse user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        logger.info("User delete requested: " + id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
