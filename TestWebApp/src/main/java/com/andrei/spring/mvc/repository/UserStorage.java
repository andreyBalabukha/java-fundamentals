package com.andrei.spring.mvc.repository;

import com.andrei.spring.mvc.model.User;
import com.andrei.spring.mvc.model.impl.UserImpl;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

public class UserStorage extends Storage<User> {
    private final String storageFilePath;
    private final StorageReader storageReader;


    public UserStorage(String storageFilePath) {
        super(new HashMap<>());
        this.storageFilePath = storageFilePath;
        this.storageReader = new StorageReader();
    }

    @PostConstruct
    private void initStorage() {
        List<String> rows = storageReader.getStorageData(storageFilePath);
        if (rows.isEmpty()) {
            return;
        }

        rows.forEach(this::writeUserToStorage);
    }

    private void writeUserToStorage(String row) {
        String[] split = row.split(",");

        try {
            long id = Long.parseLong(split[0]);
            String name = split[1];
            String email = split[2];

            User user = new UserImpl(id, name, email);

            storage.put(id, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
