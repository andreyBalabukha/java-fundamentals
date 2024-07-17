package com.andrei.spring.mvc.service;

import com.andrei.spring.mvc.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getUsersByName(String name);
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(Long id);
}
