package com.andrei.spring.mvc.service;

import com.andrei.spring.mvc.model.Event;
import com.andrei.spring.mvc.model.Ticket;
import com.andrei.spring.mvc.model.User;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    List<Ticket> getBookedTickets(User user);
    List<Ticket> getBookedTickets(Event event);
    boolean cancelTicket(long ticketId);
}
