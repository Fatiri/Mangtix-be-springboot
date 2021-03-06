package com.enigma.controller;

import com.enigma.entity.Ticket;
import com.enigma.entity.TicketCode;
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

    @CrossOrigin
    @PutMapping("/ticket")
    public Ticket updateTicketCode(@RequestBody Ticket ticket) {
        return ticketService.updateTicketCode(ticket);
    }

    @CrossOrigin
    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }

    @CrossOrigin
    @GetMapping("/tickets")
    public List<Ticket> getAllTicket(){
        return ticketService.getAllTicket();
    }

    @CrossOrigin
    @GetMapping("/ticket-list")
    public List<Ticket> getTicketsByEventDetail(@RequestParam String eventDetailId){
        return ticketService.getTicketsByEventDetail(eventDetailId);
    }

    @CrossOrigin
    @DeleteMapping("/ticket/{id}")
    public void deleteTicketById(@PathVariable String id){
        ticketService.delete(id);
    }

    @CrossOrigin
    @PostMapping("/ticket-code/{ticketCode}")
    public TicketCode findTicketCodeByTicketCode(@PathVariable String ticketCode){
        return ticketService.findTicketCodeByTicketCode(ticketCode);

    }
}
