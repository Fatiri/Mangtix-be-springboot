package com.enigma.services.impl;

import com.enigma.constanta.StringConstant;
import com.enigma.entity.Event;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.EventRepository;
import com.enigma.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event getEventId(String eventId) {
        if (!eventRepository.findById(eventId).isPresent()) {
            throw new NotFoundException(String.format(StringConstant.ID_EVENT_NOT_FOUND, eventId));
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
