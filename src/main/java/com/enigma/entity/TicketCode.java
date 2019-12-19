package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.enigma.constanta.TicketConstant;
import com.enigma.constanta.TicketCodeConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = TicketCodeConstant.MST_TICKET_CODE)
public class TicketCode {
    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    @ManyToOne
    @JoinColumn(name = TicketConstant.TICKET_ID)
    @JsonIgnore
    private Ticket ticket;
    private String ticketCode;
    private Boolean isArrived;
    private StatusTicketOut statusTicketOut;
    private Boolean isAvailable;
    @Transient
    private String ticketIdTransient;

    public TicketCode() {
    }

    public TicketCode(String ticketCode, Boolean isArrived, StatusTicketOut statusTicketOut, Boolean isAvailable, String ticketIdTransient) {
        this.ticketCode = ticketCode;
        this.isArrived = isArrived;
        this.statusTicketOut = statusTicketOut;
        this.isAvailable = isAvailable;
        this.ticketIdTransient = ticketIdTransient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Boolean getArrived() {
        return isArrived;
    }

    public void setArrived(Boolean arrived) {
        isArrived = arrived;
    }

    public StatusTicketOut getStatusTicketOut() {
        return statusTicketOut;
    }

    public void setStatusTicketOut(StatusTicketOut statusTicketOut) {
        this.statusTicketOut = statusTicketOut;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getTicketIdTransient() {
        return ticketIdTransient;
    }

    public void setTicketIdTransient(String ticketIdTransient) {
        this.ticketIdTransient = ticketIdTransient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCode that = (TicketCode) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ticket, that.ticket) &&
                Objects.equals(ticketCode, that.ticketCode) &&
                Objects.equals(isArrived, that.isArrived) &&
                statusTicketOut == that.statusTicketOut &&
                Objects.equals(isAvailable, that.isAvailable) &&
                Objects.equals(ticketIdTransient, that.ticketIdTransient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket, ticketCode, isArrived, statusTicketOut, isAvailable, ticketIdTransient);
    }
}
