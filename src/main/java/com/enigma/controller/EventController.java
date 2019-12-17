package com.enigma.controller;

import com.enigma.entity.Event;
import com.enigma.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/events")
    public List<Event> getAllEvent() {
        return eventService.getEvents();
    }

    @GetMapping("/event/{id}")
    public Event getEventById(@PathVariable String id) throws Throwable {
        return eventService.getEventById(id);
    }

    @PostMapping("/event")
    public Event saveEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }
    @PutMapping("/event")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }
    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }
}
