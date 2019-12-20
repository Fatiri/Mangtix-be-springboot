package com.enigma.controller;

import com.enigma.entity.Booking;
import com.enigma.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Booking> getAllBookingData(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookingService.getAllBookingData(pageable);
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
