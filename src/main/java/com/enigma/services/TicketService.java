package com.enigma.services;

import com.enigma.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);

    Ticket getTicketById(String id);

    List<Ticket> getAllTicket();

    void delete(String id);

    void deduct(String id, Integer quantity);

    }
