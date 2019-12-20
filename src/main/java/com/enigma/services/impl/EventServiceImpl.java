package com.enigma.services.impl;

import com.enigma.constanta.EventConstanta;
import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.Company;
import com.enigma.entity.Event;
import com.enigma.entity.EventDetail;
import com.enigma.entity.Location;
import com.enigma.exception.BadRequestException;
import com.enigma.exception.ForbiddenException;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.EventDetailRepository;
import com.enigma.repositories.EventRepository;
import com.enigma.services.CompanyService;
import com.enigma.services.EventService;
import com.enigma.services.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventDetailRepository eventDetailRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Event getEventById(String id) {
        if (!eventRepository.findById(id).isPresent()) {
            throw new NotFoundException(String.format(EventConstanta.ID_EVENT_NOT_FOUND, id));
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
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Page<Event> eventPagination(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public Event saveEventWithImage(MultipartFile multipartFile, String eventId) throws JsonProcessingException {
        Event event = saveEvent(objectMapper.readValue(eventId, Event.class));
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(EventConstanta.FILE_DIRECTORY + event.getId());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Company company = companyService.getCompanyById(event.getCompanyIdTransient());
        event.setCompany(company);
        for (EventDetail eventDetail: event.getEventDetailList()) {
            eventDetail.setEvent(event);
            Location location = locationService.getLocationById(eventDetail.getLocationIdTransient());
            eventDetail.setLocation(location);
            Date iyak = eventDetail.getEventDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String s = dateFormat.format(iyak);
//            if (eventDetailRepository.existsEventDetailByVenueLike(eventDetail.getVenue())) {
//                throw new ForbiddenException("Gabisa Woi");
//            }
        }
        return eventRepository.save(event);
    }
}
