package com.example.java_mp.service;

import com.example.java_mp.dto.request.EventDtoRequest;
import com.example.java_mp.dto.response.EventDtoResponse;
import com.example.java_mp.exceptions.EventNotFoundException;
import com.example.java_mp.model.Event;
import com.example.java_mp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private EventDtoResponse convertToDto(Event event) {
        EventDtoResponse eventDtoResponse = new EventDtoResponse();
        eventDtoResponse.setId(event.getId());
        eventDtoResponse.setTitle(event.getTitle());
        eventDtoResponse.setDate(event.getDate());

        return eventDtoResponse;
    }

    public EventDtoResponse getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);

        return event
                .map(entity -> convertToDto(entity))
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
    }

    public List<EventDtoResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDtoResponse> eventDtoResponses = new ArrayList<>();

        events.forEach(event -> eventDtoResponses.add(convertToDto(event)));
        return eventDtoResponses;
    }

    public EventDtoResponse createEvent(EventDtoRequest eventRequest) {
        Event event = new Event(eventRequest.title, eventRequest.date);

        return convertToDto(eventRepository.save(event));
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException("Event not found");
        }
        eventRepository.deleteById(id);
    }
}
