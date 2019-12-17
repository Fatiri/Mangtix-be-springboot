package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.Booking;
import com.enigma.entity.BookingDetail;
import com.enigma.entity.User;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.BookingRepository;
import com.enigma.services.BookingService;
import com.enigma.services.TicketService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;

    @Override
    public Booking booking(Booking booking) {
        User user = userService.getUserById(booking.getUserIdTransient());
        booking.setUser(user);
        for (BookingDetail bookingDetail: booking.getBookingDetailList()) {
             bookingDetail.setBooking(booking);
             System.out.println(booking);
        }
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookingData() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(String bookingId) {
        if (!bookingRepository.findById(bookingId).isPresent()){
            throw new NotFoundException(String.format(MessageConstant.ID_BOOKING_NOT_FOUND, bookingId));
        }
        return bookingRepository.findById(bookingId).get();
    }

    @Override
    public void deleteBookingDataById(String bookingId) {
            bookingRepository.deleteById(bookingId);
    }
}
