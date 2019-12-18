package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.Category;
import com.enigma.entity.Event;
import com.enigma.entity.Ticket;
import com.enigma.exception.ForbiddenException;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.TicketRepository;
import com.enigma.services.CategoryService;
import com.enigma.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class TicketServiceImpl implements com.enigma.services.TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    EventService eventService;

    @Override
    public Ticket saveTicket(Ticket ticket){
        Event event=eventService.getEventById(ticket.getEventIdTransient());
        if (event.getPublishStatus()==false){
            throw new ForbiddenException(MessageConstant.EVENT_HAS_NOT_VALIDATED);
        }
        ticket.setEvent(event);
        Category category=categoryService.getCategoryById(ticket.getCategoryIdTransient());
        ticket.setCategory(category);
        ticket.setCreateAt(Calendar.getInstance());
        return ticketRepository.save(ticket);
    }
    @Override
    public Ticket getTicketById(String id){
        if (!ticketRepository.findById(id).isPresent()){
            throw new NotFoundException(String.format(MessageConstant.ID_TICKET_NOT_FOUND,id));
        }
        return ticketRepository.findById(id).get();
    }
    @Override
    public List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }
    @Override
    public void delete(String id){
        ticketRepository.deleteById(id);
    }
    @Override
    public void deduct(String id, Integer quantity){
        Ticket ticket = getTicketById(id);
        if (ticket.getQuantity()-quantity<0){
            throw new ForbiddenException(MessageConstant.TICKET_IS_GONE);
        }
        ticket.deductQuantity(quantity);
        ticketRepository.save(ticket);
    }

    public void restoreQuantity(String id, Integer quantity){
        Ticket ticket = getTicketById(id);
        Integer sum = ticket.getQuantity()+quantity;
        ticket.setQuantity(sum);
        ticketRepository.save(ticket);
    }
}
