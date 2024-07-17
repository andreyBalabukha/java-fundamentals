package com.andrei.spring.mvc.dao;

import com.andrei.spring.mvc.model.User;
import com.andrei.spring.mvc.repository.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    private UserStorage userStorage;

    @Autowired
    void setUserStorage(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User getUserById(Long id) {
        return userStorage.getStorage().get(id);
    }

    public User getUserByEmail(String email) {
        return userStorage.getStorage().values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUserByName(String name) {
        return userStorage.getStorage().values().stream()
                .filter(user->user.getName().equals(name))
                .collect(Collectors.toList());
    }

    public User createUser(User newUser) {
        userStorage.getStorage().put(newUser.getId(), newUser);
        return userStorage.getStorage().get(newUser.getId());
    }

    public User updateUser(User newUser) {
        User oldUser = getUserById(newUser.getId());

        if (oldUser == null) {
            createUser(newUser);
        }

        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        userStorage.getStorage().put(oldUser.getId(), oldUser);
        return getUserById(oldUser.getId());
    }

    public boolean deleteUser(Long id) {
        userStorage.getStorage().remove(id);
        return true;
    }
}
