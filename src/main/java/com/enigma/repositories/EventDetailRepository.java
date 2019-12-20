package com.enigma.repositories;

import com.enigma.entity.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventDetailRepository extends JpaRepository<EventDetail,String> {

    Boolean existsEventDetailByVenueLike(String venue);
    Boolean existsEventDetailByEventDateLike(String eventDate);
}
