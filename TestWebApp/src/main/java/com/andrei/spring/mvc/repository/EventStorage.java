package com.andrei.spring.mvc.repository;

import com.andrei.spring.mvc.model.Event;
import com.andrei.spring.mvc.model.User;
import com.andrei.spring.mvc.model.impl.EventImpl;
import com.andrei.spring.mvc.model.impl.UserImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EventStorage extends Storage<Event> {
    private final String storageFilePath;
    private final StorageReader storageReader;

    public EventStorage(String storageFilePath) {
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

        rows.forEach(this::writeEventToStorage);
    }

    private void writeEventToStorage(String row) {
        String[] split = row.split(",");

        try {
            long id = Long.parseLong(split[0]);
            String title = split[1];
            Date date = new Date(Long.parseLong(split[2]));

            Event event = new EventImpl(id, title, date);

            storage.put(id, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
