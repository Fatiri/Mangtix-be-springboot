package com.enigma.services.impl;

import com.enigma.entity.Event;
import com.enigma.repositories.EventRepository;
import com.enigma.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EventServiceImplTest {

    @Autowired
    EventService eventService;

    @MockBean
    EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        eventRepository.deleteAll();
    }

    @Test
    public void getEventById_should_callEventRepository_findById_once() {
        String eventId = "815cabfc-f935-4453-8dc3-0534d8c0171b";
        String nameEvent = "Hey Fest !";
        String descriptionEvent = "Konser Kawula Muda di Bekasi !";
        Date eventDate = new Date();
        String eventLocation = "Bekasi, Summarecon mal Bekasi";
        int expectedCall = 2;
        Boolean publishStatus = false;
        Event event = new Event(nameEvent, descriptionEvent, eventDate, eventLocation, publishStatus);
        event.setId(eventId);
        eventService.saveEvent(event);

        Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        eventService.getEventById(eventId);
        Mockito.verify(eventRepository, Mockito.times(expectedCall)).findById(eventId);
    }

    @Test
    void getEvents() {

        String eventId = "815cabfc-f935-4453-8dc3-0534d8c0171b";
        String nameEvent = "Hey Fest !";
        String descriptionEvent = "Konser Kawula Muda di Bekasi !";
        Date eventDate = new Date();
        String eventLocation = "Bekasi, Summarecon mal Bekasi";
        Boolean publishStatus = false;
        Event event = new Event(nameEvent, descriptionEvent, eventDate, eventLocation, publishStatus);
        event.setId(eventId);
        eventService.saveEvent(event);

        String id = "815cabfc-f935-4453-8dc3-0534d8c0171b";
        String name = "Hey Fest !";
        String description = "Konser Kawula Muda di Bekasi !";
        Date date = new Date();
        String location = "Bekasi, Summarecon mal Bekasi";
        Boolean publishStatusConcert = false;
        Event event2 = new Event(name, description,date, location, publishStatusConcert);
        event.setId(id);

        int expectedCalling = 1;

        eventService.saveEvent(event2);
        eventService.getEvents();

        Mockito.verify(eventRepository, Mockito.times(expectedCalling)).findAll();
    }

    @Test
    void deleteEvent() {
        String eventId = "815cabfc-f935-4453-8dc3-0534d8c0171b";
        String nameEvent = "Hey Fest !";
        String descriptionEvent = "Konser Kawula Muda di Bekasi !";
        Date eventDate = new Date();
        String eventLocation = "Bekasi, Summarecon mal Bekasi";

        int expectedCalling = 1;

        Boolean publishStatus = false;
        Event event = new Event(nameEvent, descriptionEvent, eventDate, eventLocation, publishStatus);
        event.setId(eventId);

        eventService.saveEvent(event);
        eventService.deleteEvent(eventId);

        Mockito.verify(eventRepository, Mockito.times(expectedCalling)).deleteById(eventId);
    }

    @Test
    void saveEvent_should_callEventRepository_save_once() {
        String eventId = "815cabfc-f935-4453-8dc3-0534d8c0171b";
        String nameEvent = "Hey Fest !";
        String descriptionEvent = "Konser Kawula Muda di Bekasi !";
        Date eventDate = new Date();
        String eventLocation = "Bekasi, Summarecon mal Bekasi";
        int expectedCall = 1;
        Boolean publishStatus = false;
        Event event = new Event(nameEvent, descriptionEvent, eventDate, eventLocation, publishStatus);
        event.setId(eventId);
        eventService.saveEvent(event);
        Mockito.verify(eventRepository, Mockito.times(expectedCall)).save(event);
    }
}
