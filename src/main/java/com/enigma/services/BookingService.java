package com.enigma.services;

import com.enigma.entity.Booking;

import java.util.List;

public interface BookingService {

    // just save insert booking data for now, for test api , logic if quantity ticket and other coming soon after all feature working
    public Booking booking(Booking booking);
    public List<Booking> getAllBookingData();
    public Booking getBookingById(String bookingId);
    public void deleteBookingDataById(String bookingId);

}
