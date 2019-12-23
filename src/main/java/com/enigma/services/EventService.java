package com.enigma.services;

import com.enigma.entity.Company;
import com.enigma.entity.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    public Event getEventById(String eventId);
    public List<Event> getEvents();
    public void deleteEvent(String eventId);
    public Event saveEvent(Event event);
    public Event saveEventWithImage(MultipartFile multipartFile,MultipartFile multipartImage, String event) throws JsonProcessingException;
    public Event updateEvent(Event event);
    public Page<Event> eventPagination(Pageable pageable);
    public List<Event> getEventByCompany(Company company);
}
