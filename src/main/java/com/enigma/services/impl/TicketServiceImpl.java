package com.enigma.services.impl;

import com.enigma.constanta.BookingConstant;
import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
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

    @Override
    public Ticket updateTicketCode(Ticket ticket) {
        Ticket ticketObject = getTicketById(ticket.getId());
        List<TicketCode> ticketCodeOnSale = new ArrayList<>();
        List<TicketCode> ticketCodeFree = new ArrayList<>();
        if (ticket.getOnSaleTransient()!=0){
            setStatusCodeOutOnSale(ticket, ticketObject, ticketCodeOnSale);
        }
        if (ticket.getFreeTransient()!=0){
            setStatusCodeOutFree(ticket, ticketObject, ticketCodeFree);
        }

        return ticketRepository.save(ticket);
    }

    private void setStatusCodeOutFree(Ticket ticket, Ticket ticketObject, List<TicketCode> ticketCodeFree) {
        for (TicketCode ticketCode : ticketObject.getTicketCodes()) {

            if (ticketCode.getStatusTicketOut().equals(StatusTicketOut.WAITING)){
                ticketCode.setAvailable(false);
                ticketCode.setStatusTicketOut(StatusTicketOut.FREE);
                ticketCodeFree.add(ticketCode);
            }

            if (ticketCodeFree.size()>=ticket.getOnSaleTransient()){
                break;
            }
        }
    }

    private void setStatusCodeOutOnSale(Ticket ticket, Ticket ticketObject, List<TicketCode> ticketCodeOnSale) {
        for (TicketCode ticketCode : ticketObject.getTicketCodes()) {

            if (ticketCode.getStatusTicketOut().equals(StatusTicketOut.WAITING)){
                ticketCode.setAvailable(true);
                ticketCode.setStatusTicketOut(StatusTicketOut.ON_SALE);
                ticketCodeOnSale.add(ticketCode);
            }

            if (ticketCodeOnSale.size()>=ticket.getOnSaleTransient()){
                break;
            }

        }
    }

    public Ticket setAvailableAfterBooking(Ticket ticket, Integer quantity){
        Ticket ticketObject = getTicketById(ticket.getId());
        List<TicketCode> ticketCodeOnSale = new ArrayList<>();
        for (TicketCode ticketCode : ticketObject.getTicketCodes()) {
            if (ticketCode.getAvailable().equals(true)) {
                ticketCode.setAvailable(false);
                ticketCodeOnSale.add(ticketCode);
            }

            if (ticketCodeOnSale.size()>=quantity){
                break;
            }
        }

        if (ticketCodeOnSale.size()==0){
            System.out.println(ticketCodeOnSale.size());
            throw new ForbiddenException(BookingConstant.TICKET_NOT_AVAILABLE);
        }
        return ticketRepository.save(ticket);
    }

}
