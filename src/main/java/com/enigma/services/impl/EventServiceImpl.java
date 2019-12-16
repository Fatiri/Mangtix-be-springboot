package com.enigma.services.impl;

import com.enigma.entity.Event;
import com.enigma.repositories.EventRepository;
import com.enigma.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    private Object Exception;

    @Override
    public Event getEventId(String eventId) throws Throwable {
        if (!eventRepository.findById(eventId).isPresent()) {
            throw (Throwable) Exception;
        }
        return eventRepository.findById(eventId).get();
    }

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
