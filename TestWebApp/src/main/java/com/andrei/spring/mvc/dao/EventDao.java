package com.andrei.spring.mvc.dao;

import com.andrei.spring.mvc.repository.EventStorage;
import com.andrei.spring.mvc.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventDao {
    private EventStorage eventStorage;

    @Autowired
    void setEventDao(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    public Event getEventById(long id) {
        return eventStorage.getStorage().get(id);
    }

    public List<Event> getEventsByTitle(String title) {
        return eventStorage
                .getStorage()
                .values()
                .stream()
                .filter(event -> event.getTitle().contains(title))
                .toList();
    }

    public List<Event> getEventsForDay(Date day) {
        return eventStorage
                .getStorage()
                .values()
                .stream()
                .filter(event -> event.getDate().equals(day))
                .collect(Collectors.toList());
    }

    public Event createEvent(Event event) {
        eventStorage.getStorage().put(event.getId(), event);
        return event;
    }

    public Event updateEvent(Event newEvent) {
        Event oldEvent = getEventById(newEvent.getId());
        oldEvent.setTitle(newEvent.getTitle());
        oldEvent.setDate(newEvent.getDate());
        eventStorage.getStorage().put(oldEvent.getId(), oldEvent);
        return getEventById(newEvent.getId());
    }

    public boolean deleteEvent(long id) {
        eventStorage.getStorage().remove(id);
        return true;
    }
}
