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
        booking.setBookingDetailList(booking.getBookingDetailList());
        for (BookingDetail bookingDetailList: booking.getBookingDetailList()) {
            bookingDetailList.setBooking(booking);
            Ticket ticket = ticketService.getTicketById(bookingDetailList.getTicketIdTransient());
            bookingDetailList.setTicket(ticket);
            bookingDetailList.setSubtotal(bookingDetailList.getTicket().getPrice().multiply(new BigDecimal(bookingDetailList.getQuantity())));
            ticketService.setAvailableAfterBooking(ticket, bookingDetailList.getQuantity());
        }
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
