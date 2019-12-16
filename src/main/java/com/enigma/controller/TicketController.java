package com.enigma.controller;

import com.enigma.entity.Ticket;
import com.enigma.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @CrossOrigin
    @PostMapping("/ticket")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }
    @GetMapping("/tickets")
    public List<Ticket> getAllTicket(){
        return ticketService.getAllTicket();
    }
    @DeleteMapping("/ticket/{id}")
    public void deleteTicketById(@PathVariable String id){
        ticketService.delete(id);
    }
}
