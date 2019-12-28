package com.enigma.entity;

import com.enigma.constanta.EventConstanta;
import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = EventConstanta.EVENT_DETAIL_TABLE)
public class EventDetail {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    private String venue;
    private String description;
    private String eventDay;
    @JsonFormat(pattern = EventConstanta.EVENT_DATE_PATTERN)
    private Date eventDate;
    @ManyToOne
    @JoinColumn(name = EventConstanta.EVENT_ID_RELATION)
    @JsonIgnore
    private Event event;
    @ManyToOne
    @JoinColumn(name = EventConstanta.LOCATION_ID_RELATION)
    private Location location;
    @Transient
    private String locationIdTransient;
    @OneToMany(mappedBy = EventConstanta.EVENT_DETAIL_MAPPED_BY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Ticket> tickets;

    public EventDetail(String locationName, String description, String eventDay, Date eventDate) {
        this.venue = locationName;
        this.description = description;
        this.eventDay = eventDay;
        this.eventDate = eventDate;
    }

    public EventDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDay() {
        return eventDay;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationIdTransient() {
        return locationIdTransient;
    }

    public void setLocationIdTransient(String locationIdTransient) {
        this.locationIdTransient = locationIdTransient;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
