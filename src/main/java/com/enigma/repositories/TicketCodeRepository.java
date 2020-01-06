package com.enigma.repositories;

import com.enigma.entity.TicketCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCodeRepository extends JpaRepository <TicketCode, String> {
    TicketCode findTicketCodeByTicketCode(String ticketCode);
}
