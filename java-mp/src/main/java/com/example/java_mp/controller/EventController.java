package com.example.java_mp.controller;

import com.example.java_mp.dto.request.EventDtoRequest;
import com.example.java_mp.dto.response.EventDtoResponse;
import com.example.java_mp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EventDtoResponse>> getAllEvents() {
        try {
            List<EventDtoResponse> eventsDto = eventService.getAllEvents();
            return ResponseEntity.ok(eventsDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDtoResponse> getEventById(@PathVariable Long id) {
        try {
            EventDtoResponse eventDtoResponse = eventService.getEventById(id);
            return ResponseEntity.ok(eventDtoResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<EventDtoResponse> createEvent(@RequestBody EventDtoRequest event) {

        try {
            EventDtoResponse eventDtoResponse = eventService.createEvent(event);
            return ResponseEntity.ok(eventDtoResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}