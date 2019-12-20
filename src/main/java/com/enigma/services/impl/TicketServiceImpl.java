package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.*;
import com.enigma.exception.ForbiddenException;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.TicketRepository;
import com.enigma.services.CategoryService;
import com.enigma.services.EventService;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TicketServiceImpl implements com.enigma.services.TicketService {
    @GeneratedValue (generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String ticketCode;
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

        String code ;
        Integer sum ;
        List<TicketCode> ticketCodeList = new ArrayList<>();

            for (int i = 0; i <ticket.getQuantity(); i++) {
                sum = i;
                TicketCode ticketCode = new TicketCode();
                ticketCode.setTicket(ticket);
                code = ticket.getEvent().getId() + ticketCode.getTicketIdTransient()+ ticket.getCategory().getCategoryName() + sum;
                ticketCode.setTicketCode(code);
                ticketCode.setStatusTicketOut(StatusTicketOut.WAITING);
                ticketCode.setAvailable(false);
                ticketCode.setArrived(false);
                ticketCodeList.add(ticketCode);
            }
            ticket.setTicketCodes(ticketCodeList);




//        for (int i = 1; i <= quantity; i++) {
//            sum=i;
//        for (TicketCode ticketCode : ticket.getTicketCodes()) {
//
//                ticketCode.setTicket(ticket);
//                code = ticket.getEvent().getId() + ticket.getId() + ticket.getCategory().getCategoryName() + sum;
//                ticketCode.setTicketCode(code);
//                if (ticketCode.getStatusTicketOut().equals(StatusTicketOut.ON_SALE)) {
//                    ticketCode.setAvailable(true);
//                } else if (ticketCode.getStatusTicketOut().equals(StatusTicketOut.FREE)){
//                    ticketCode.setAvailable(false);
//                }else {
//                    ticketCode.setStatusTicketOut(StatusTicketOut.ON_SALE);
//                    ticketCode.setAvailable(true);
//                }
//                ticketCode.setArrived(false);
//            }
//        }
        return ticketRepository.save(ticket);
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
