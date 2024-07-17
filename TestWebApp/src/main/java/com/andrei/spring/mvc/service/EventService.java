package com.andrei.spring.mvc.service;

import com.andrei.spring.mvc.model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {
    Event getEventById(long id);
    List<Event> getEventsByTitle(String title);
    List<Event> getEventsForDay(Date day);
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
