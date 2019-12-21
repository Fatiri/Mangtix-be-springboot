package com.enigma.spesification;

import com.enigma.entity.Booking;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class BookingSpecification {
    public static Specification<Booking> findByCriteria(Booking booking) {
        return new Specification<Booking>() {
            @Override
            public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                final Collection<Predicate> predicate = new ArrayList<>();

                if (!StringUtils.isEmpty(booking.getId())) {
                    final Predicate idPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("id")), "%" + booking.getId() + "%");
                    predicate.add(idPredicate);
                }

                return criteriaBuilder.or(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
    }
}
