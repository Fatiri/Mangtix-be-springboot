package com.enigma.repositories;

import com.enigma.entity.Event;
import com.enigma.entity.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    Event findEventByEventDetailList(EventDetail eventDetail);
}
