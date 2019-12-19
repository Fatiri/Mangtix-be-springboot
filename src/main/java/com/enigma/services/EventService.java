package com.enigma.services;

import com.enigma.entity.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    public Event getEventById(String eventId);
    public List<Event> getEvents();
    public void deleteEvent(String eventId);
    public Event saveEvent(Event event);
    public Event saveEventWithImage(MultipartFile multipartFile, String event) throws JsonProcessingException;
    public Event updateEvent(Event event);
}
