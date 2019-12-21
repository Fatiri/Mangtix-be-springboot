package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.*;
import com.enigma.exception.ForbiddenException;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.TicketRepository;
import com.enigma.services.CategoryService;
import com.enigma.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements com.enigma.services.TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    EventService eventService;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        Event event = eventService.getEventById(ticket.getEventIdTransient());
        if (!event.getPublishStatus()) {
            throw new ForbiddenException(MessageConstant.EVENT_HAS_NOT_VALIDATED);
        }
        ticket.setEvent(event);
        Category category = categoryService.getCategoryById(ticket.getCategoryIdTransient());
        ticket.setCategory(category);
        ticket.setCreateAt(Calendar.getInstance());
        List<TicketCode> ticketCodeList = generatedTicketCode(ticket);
        ticket.setTicketCodes(ticketCodeList);

        return ticketRepository.save(ticket);
    }

    private List<TicketCode> generatedTicketCode(Ticket ticket) {
        Integer sum;
        String code;
        List<TicketCode> ticketCodeList = new ArrayList<>();
        for (int i = 0; i <ticket.getQuantity(); i++) {
            sum = i;
            TicketCode ticketCode = new TicketCode();
            ticketCode.setTicket(ticket);
            String uuid = UUID.randomUUID().toString();
            code = ticket.getCategory().getCategoryName()+sum+"-"+ ticket.getEvent().getId()+"-"+ uuid;
            ticketCode.setTicketCode(code);
            ticketCode.setStatusTicketOut(StatusTicketOut.WAITING);
            ticketCode.setAvailable(false);
            ticketCode.setArrived(false);
            ticketCodeList.add(ticketCode);
        }
        return ticketCodeList;
    }

    @Override
    public Ticket getTicketById(String id) {
        if (!ticketRepository.findById(id).isPresent()) {
            throw new NotFoundException(String.format(MessageConstant.ID_TICKET_NOT_FOUND, id));
        }
        return ticketRepository.findById(id).get();
    }

    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    @Override
    public void delete(String id) {
        ticketRepository.deleteById(id);
    }

}
