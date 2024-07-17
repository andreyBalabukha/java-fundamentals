package com.andrei.spring.mvc.dao;

import com.andrei.spring.mvc.model.Event;
import com.andrei.spring.mvc.model.Ticket;
import com.andrei.spring.mvc.model.User;
import com.andrei.spring.mvc.model.impl.TicketImpl;
import com.andrei.spring.mvc.repository.TicketStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TicketDao {
    private TicketStorage ticketStorage;

    @Autowired
    void setTicketDao(TicketStorage ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        Ticket ticket = new TicketImpl(id, userId, eventId, place, category);
        ticketStorage.getStorage().put(id, ticket);
        return ticket;
    }

    public List<Ticket> getBookedTickets(User user) {
        long userId = user.getId();

        return ticketStorage
                .getStorage()
                .values()
                .stream()
                .filter(ticket -> ticket.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public List<Ticket> getBookedTickets(Event event) {
        long eventId = event.getId();

        return ticketStorage
                .getStorage()
                .values()
                .stream()
                .filter(ticket -> ticket.getEventId() == eventId)
                .collect(Collectors.toList());
    }

    public boolean cancelTicket(long ticketId){
        ticketStorage.getStorage().remove(ticketId);
        return true;
    }
}
