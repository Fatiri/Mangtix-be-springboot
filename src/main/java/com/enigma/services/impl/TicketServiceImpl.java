package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.Category;
import com.enigma.entity.Event;
import com.enigma.entity.Ticket;
import com.enigma.entity.TicketCode;
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
        for (TicketCode ticketCode:ticket.getTicketCodes()){
            ticketCode.setTicket(ticket);
            Integer quantity=ticket.getQuantity();
            String code;
            for (int i = 1; i <= quantity; i++) {
                code=ticket.getEvent().getId()+ticket.getId()+ticket.getCategory().getCategoryName()+i;
                ticketCode.setTicketCode(code);
            }
        }
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

}
