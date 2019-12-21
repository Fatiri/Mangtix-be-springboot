package com.enigma.controller;

import com.enigma.entity.Booking;
import com.enigma.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    public Page<Booking> getAllBookingData(@RequestParam Integer page, @RequestParam Integer size, @RequestBody Booking bookingFrm) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Booking> bookingExample = Example.of(bookingFrm,exampleMatcher);

        Pageable pageable = PageRequest.of(page, size);
        return bookingService.getAllBookingData(bookingExample,pageable);
    }

    @GetMapping("/booking")
    public Booking getBookingById(@RequestBody String bookingId){

        return bookingService.getBookingById(bookingId);
    }

    @DeleteMapping("/booking")
    public void deleteBookingById(@RequestBody String bookingId){
        bookingService.deleteBookingDataById(bookingId);
    }
}
