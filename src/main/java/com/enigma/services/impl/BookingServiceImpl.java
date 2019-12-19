package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.*;
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
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setBooking(booking);

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
