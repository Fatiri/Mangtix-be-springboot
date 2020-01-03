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

    @CrossOrigin
    @PostMapping("/booking")
    public Booking booking(@RequestBody Booking booking){
        return bookingService.booking(booking);
    }

    @CrossOrigin
    @GetMapping("/bookings")
    public Page<Booking> getAllBookingData(@RequestParam Integer page, @RequestParam Integer size, @RequestBody Booking bookingFrm) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Booking> bookingExample = Example.of(bookingFrm,exampleMatcher);

        Pageable pageable = PageRequest.of(page, size);
        return bookingService.getAllBookingData(bookingExample,pageable);
    }

    @CrossOrigin
    @GetMapping("/booking/{id}")
    public Booking getBookingById(@PathVariable String id){
        return bookingService.getBookingById(id);
    }

    @CrossOrigin
    @DeleteMapping("/booking")
    public void deleteBookingById(@RequestBody String bookingId){
        bookingService.deleteBookingDataById(bookingId);
    }

    @CrossOrigin
    @GetMapping("/booking-user")
    public List<Booking> getBookingByUser(@RequestParam String userId){
       return bookingService.getBookingByUser(userId);
    }

    @CrossOrigin
    @GetMapping("/booking-list")
    public List<Booking> getAllBooking(){
        return bookingService.getBookings();
    }
}
