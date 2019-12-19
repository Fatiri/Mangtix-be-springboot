package com.enigma.services.impl;

import com.enigma.constanta.BookingConstant;
import com.enigma.constanta.MessageConstant;
import com.enigma.entity.*;
import com.enigma.exception.ForbiddenException;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.BookingRepository;
import com.enigma.services.BookingService;
import com.enigma.services.EventService;
import com.enigma.services.TicketService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;
    @Autowired
    EventService eventService;


    @Override
    public Booking booking(Booking booking) {
        User user = userService.getUserById(booking.getUserIdTransient());
        booking.setUser(user);
        List<TicketCode> remainingTicketAvailable = new ArrayList<>();
        Integer penampung =0;

        for (BookingDetail bookingDetailList: booking.getBookingDetailList()) {
            Ticket ticket = ticketService.getTicketById(bookingDetailList.getTicketIdTransient());
            bookingDetailList.setTicket(ticket);
            for (TicketCode ticketCodeListOne: bookingDetailList.getTicket().getTicketCodes()) {
                if (ticketCodeListOne.getAvailable()){
                    remainingTicketAvailable.add(ticketCodeListOne);
                    for (int i = 1; i <=remainingTicketAvailable.size() ; i++) {
                        penampung = i;
                        if (bookingDetailList.getQuantity()<=penampung){
                            ticketCodeListOne.setAvailable(true);
                            bookingDetailList.setSubtotal(bookingDetailList.getTicket().getPrice().multiply(new BigDecimal(bookingDetailList.getQuantity())));
                        }
                    }
                }
                throw new ForbiddenException(BookingConstant.TICKET_NOT_AVAILABLE);
            }

        }
        System.out.println(penampung);
        return bookingRepository.save(booking);
    }

    @Override
    public Page<Booking> getAllBookingData(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Booking getBookingById(String bookingId) {
        if (!bookingRepository.findById(bookingId).isPresent()) {
            throw new NotFoundException(String.format(MessageConstant.ID_BOOKING_NOT_FOUND, bookingId));
        }
        return bookingRepository.findById(bookingId).get();
    }

    @Override
    public void deleteBookingDataById(String bookingId) {
        if (!bookingRepository.findById(bookingId).isPresent()) {
            throw new NotFoundException(String.format(MessageConstant.ID_BOOKING_NOT_FOUND, bookingId));
        }
        bookingRepository.deleteById(bookingId);
    }

}
