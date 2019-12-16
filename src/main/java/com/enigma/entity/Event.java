package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mst_event")
public class Event {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String eventId;
    private String eventName;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date eventDate;
    private String eventLocation;
    private Boolean publishStatus;
    private String permissionLatter;

    public Event() {
    }

    public Event(String eventName, String description, Date eventDate, String eventLocation, Boolean publishStatus) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.publishStatus = publishStatus;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId) &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(description, event.description) &&
                Objects.equals(eventDate, event.eventDate) &&
                Objects.equals(eventLocation, event.eventLocation) &&
                Objects.equals(publishStatus, event.publishStatus) &&
                Objects.equals(permissionLatter, event.permissionLatter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, description, eventDate, eventLocation, publishStatus, permissionLatter);
    }
}
