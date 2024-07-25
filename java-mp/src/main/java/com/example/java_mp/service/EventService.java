package com.example.java_mp.service;

import com.example.java_mp.dto.EventDto;
import com.example.java_mp.model.Event;
import com.example.java_mp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private EventDto convertToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setTitle(event.getTitle());
        eventDto.setDate(event.getDate());

        return eventDto;
    }

    public EventDto getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);

        return event
                .map(entity -> convertToDto(entity))
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
