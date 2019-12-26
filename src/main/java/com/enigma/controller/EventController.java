package com.enigma.controller;

import com.enigma.entity.Company;
import com.enigma.entity.Event;
import com.enigma.services.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @CrossOrigin
    @GetMapping("/events")
    public List<Event> getAllEvent() {
        return eventService.getEvents();
    }

    @CrossOrigin
    @GetMapping("/event/{id}")
    public Event getEventById(@PathVariable String id) throws Throwable {
        return eventService.getEventById(id);
    }

    @CrossOrigin
    @PutMapping("/event")
    public Event saveEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }
    @PostMapping("/event")
    public Event updateEvent(@RequestPart MultipartFile multipartFile, @RequestPart MultipartFile multipartImage, @RequestPart String event) throws JsonProcessingException {
        return eventService.saveEventWithImage(multipartFile,multipartImage,event);
    }

    @CrossOrigin
    @GetMapping("/event-list")
    public Page<Event> eventPagination (@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return eventService.eventPagination(pageable);
    }

    @CrossOrigin
    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }

    @CrossOrigin
    @GetMapping("/event-company")
    public List<Event> getEventByCompany(@RequestBody Company company){
        return eventService.getEventByCompany(company);
    }
}
