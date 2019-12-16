package com.enigma.services;

import com.enigma.entity.Event;

import java.util.List;

public interface EventService {

    public Event getEventId(String eventId);
    public List<Event> getEvents();
    public void deleteEvent(String eventId);
    public Event saveEvent(Event event);
}
