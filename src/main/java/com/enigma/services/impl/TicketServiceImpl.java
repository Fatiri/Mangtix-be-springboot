package com.enigma.services.impl;

import com.enigma.constanta.StringConstant;
import com.enigma.entity.Category;
import com.enigma.entity.Event;
import com.enigma.entity.Ticket;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.TicketRepository;
import com.enigma.services.CategoryService;
import com.enigma.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    EventService eventService;

    public Ticket saveTicket(Ticket ticket){
        Category category=categoryService.getCategoryById(ticket.getCategoryIdTransient());
        ticket.setCategory(category);
        Event event=eventService.getEventId(ticket.getEventIdTransient());
        ticket.setEvent(event);
        return ticketRepository.save(ticket);
    }
    public Ticket getTicketById(String id){
        if (!ticketRepository.findById(id).isPresent()){
            throw new NotFoundException(String.format(StringConstant.ID_TICKET_NOT_FOUND,id));
        }
        return ticketRepository.findById(id).get();
    }
    public List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }
    public void delete(String id){
        ticketRepository.deleteById(id);
    }
}
