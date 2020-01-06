package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = StringConstant.TRX_BOOKING_DETAIL)
public class BookingDetail {

    @Id
    @GeneratedValue (generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    private Integer quantity;
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = StringConstant.BOOKING_ID)
    @JsonIgnore
    private Booking booking;

    @Transient
    private String bookingIdTransient;

    @ManyToOne
    @JoinColumn (name = StringConstant.TICKET_ID)
    private Ticket ticket;
    @Transient
    private String ticketIdTransient;

    public BookingDetail() {
    }

    public BookingDetail(Integer quantity, BigDecimal subtotal) {
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getTicketIdTransient() {
        if (ticketIdTransient==null){
            return getTicket().getId();
        }
        return ticketIdTransient;
    }

    public String getBookingIdTransient() {
        if (bookingIdTransient==null){
            return getBooking().getId();
        }
        return bookingIdTransient;
    }

    public void setBookingIdTransient(String bookingIdTransient) {
        this.bookingIdTransient = bookingIdTransient;
    }

    public void setTicketIdTransient(String ticketIdTransient) {
        this.ticketIdTransient = ticketIdTransient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDetail that = (BookingDetail) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, subtotal);
    }

    @Override
    public String toString() {
        return "BookingDetail{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", booking=" + booking +
                ", ticket=" + ticket +
                ", ticketIdTransient='" + ticketIdTransient + '\'' +
                '}';
    }
}
