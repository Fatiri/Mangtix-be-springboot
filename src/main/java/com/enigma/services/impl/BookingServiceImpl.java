package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.*;
import com.enigma.exception.ForbiddenException;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.BookingRepository;
import com.enigma.services.BookingService;
import com.enigma.services.EventService;
import com.enigma.services.TicketService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
        booking.setPaymentStatus(false);
        BigDecimal sumSubtotal;

        for (BookingDetail bookingDetail : booking.getBookingDetailList()) {
            bookingDetail.setBooking(booking);
            Ticket ticket = ticketService.getTicketById(bookingDetail.getTicketIdTransient());
            bookingDetail.setTicket(ticket);

            ticketService.deduct(bookingDetail.getTicket().getId(), bookingDetail.getQuantity());
            sumSubtotal = ticket.getPrice().multiply(new BigDecimal(bookingDetail.getQuantity()));
            bookingDetail.setSubtotal(sumSubtotal);

        }
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookingData() {
        return bookingRepository.findAll();
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
        Booking booking = getBookingById(bookingId);
        for (BookingDetail bookingDetail : booking.getBookingDetailList()) {
            String idTicket = bookingDetail.getTicket().getId();
            Ticket ticket = ticketService.getTicketById(idTicket);
            ticket.setQuantity(ticket.getQuantity() + bookingDetail.getQuantity());
        }
        bookingRepository.deleteById(bookingId);
    }

}
