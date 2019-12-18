package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_event")
public class Event {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    private String eventName;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date eventDate;
    private String eventLocation;
    private Boolean publishStatus = false;
    private String permissionLatter;

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Ticket> tickets;

    public Event() {
    }

    public Event(String eventName, String description, Date eventDate, String eventLocation, Boolean publishStatus) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.publishStatus = publishStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Boolean getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Boolean publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getPermissionLatter() {
        return permissionLatter;
    }

    public void setPermissionLatter(String permissionLatter) {
        this.permissionLatter = permissionLatter;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(description, event.description) &&
                Objects.equals(eventDate, event.eventDate) &&
                Objects.equals(eventLocation, event.eventLocation) &&
                Objects.equals(publishStatus, event.publishStatus) &&
                Objects.equals(permissionLatter, event.permissionLatter) &&
                Objects.equals(tickets, event.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName, description, eventDate, eventLocation, publishStatus, permissionLatter, tickets);
    }
}
