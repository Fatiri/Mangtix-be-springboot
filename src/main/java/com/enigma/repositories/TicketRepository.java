package com.enigma.repositories;

import com.enigma.entity.EventDetail;
import com.enigma.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findTicketsByEventDetail(EventDetail eventDetail);
}
