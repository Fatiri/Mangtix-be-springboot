package com.enigma.services;

import com.enigma.entity.Ticket;
import com.enigma.entity.TicketCode;

import java.util.List;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);

    Ticket getTicketById(String id);

    List<Ticket> getAllTicket();

    void delete(String id);

    Ticket updateTicketCode(Ticket ticket);

    Ticket setAvailableAfterBooking(Ticket ticket, Integer quantity);

    List<Ticket> getTicketsByEventDetail(String eventDetailId);

    TicketCode findTicketCodeByTicketCode(String ticketCode);
}
