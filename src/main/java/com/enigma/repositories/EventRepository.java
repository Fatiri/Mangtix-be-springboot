package com.enigma.repositories;

import com.enigma.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    Boolean existsEventByEventLocationLike(String eventLocation);
    Boolean existsEventByEventDateLike(String eventDate);
}
