package com.andrei.spring.mvc.service.impl;

import com.andrei.spring.mvc.dao.TicketDao;
import com.andrei.spring.mvc.model.Event;
import com.andrei.spring.mvc.model.Ticket;
import com.andrei.spring.mvc.model.User;
import com.andrei.spring.mvc.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    TicketDao ticketDao;

    @Autowired
    void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketDao.bookTicket(userId, eventId, place, category);
    }

    public List<Ticket> getBookedTickets(User user) {
        return ticketDao.getBookedTickets(user);
    }

    public List<Ticket> getBookedTickets(Event event) {
        return ticketDao.getBookedTickets(event);
    }

    public boolean cancelTicket(long ticketId) {
        ticketDao.cancelTicket(ticketId);
        return true;
    }
}
