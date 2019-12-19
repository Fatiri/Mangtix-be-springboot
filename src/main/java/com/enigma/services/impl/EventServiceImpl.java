package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.Event;
import com.enigma.exception.BadRequestException;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.EventRepository;
import com.enigma.services.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    public static final String fileDir = "/var/www/html/image/";
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Event getEventById(String id) {
        if (!eventRepository.findById(id).isPresent()) {
            throw new NotFoundException(String.format(MessageConstant.ID_EVENT_NOT_FOUND, id));
        }
        return eventRepository.findById(id).get();
    }

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event saveEvent(Event event) {
        if (eventRepository.existsEventByEventLocationLike(event.getEventLocation())) {
                throw new BadRequestException(MessageConstant.EVENT_LOCATION_AND_DATE_TIME_CANNOT_SAME);
        }
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event saveEventWithImage(MultipartFile multipartFile, String eventId) throws JsonProcessingException {
        Event event = saveEvent(objectMapper.readValue(eventId, Event.class));
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(fileDir + event.getId());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventRepository.save(event);
    }
}
