package com.enigma.controller;

import com.enigma.entity.Booking;
import com.enigma.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/booking")
    public Booking booking(@RequestBody Booking booking){
        return bookingService.booking(booking);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookingData(){
        return bookingService.getAllBookingData();
    }

    @GetMapping("/booking/{bookingId}")
    public Booking getBookingById(@PathVariable String bookingId){
        return bookingService.getBookingById(bookingId);
    }

    @DeleteMapping("/booking/{bookingId}")
    public void deleteBookingById(@PathVariable String bookingId){
        bookingService.deleteBookingDataById(bookingId);
    }
}
