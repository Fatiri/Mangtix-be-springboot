package com.enigma.repositories;

import com.enigma.entity.Booking;
import com.enigma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository <Booking, String> {
    List <Booking> findBookingsByUser(User user);
}
