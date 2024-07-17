package com.andrei.spring.mvc.repository;

import com.andrei.spring.mvc.model.Ticket;
import com.andrei.spring.mvc.model.impl.TicketImpl;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

public class TicketStorage extends Storage<Ticket> {
    private final String storageFilePath;
    private final StorageReader storageReader;


    public TicketStorage(String storageFilePath) {
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

        rows.forEach(this::writeTicketToStorage);
    }

    private void writeTicketToStorage(String row) {
        String[] split = row.split(",");

        try {
            long id = Long.parseLong(split[0]);
            long eventId = Long.parseLong(split[1]);
            long userId = Long.parseLong(split[2]);
            Ticket.Category category = Ticket.Category.valueOf(split[3]);
            int place = Integer.parseInt(split[4]);


            Ticket ticket = new TicketImpl(id, eventId, userId, place, category);

            storage.put(id, ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
