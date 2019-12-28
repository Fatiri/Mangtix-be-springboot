package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.enigma.constanta.TicketConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = TicketConstant.MST_TICKET)
public class Ticket {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    @ManyToOne
    @JoinColumn(name = TicketConstant.CATEGORY_ID)
    private Category category;
    @ManyToOne
    @JoinColumn(name = TicketConstant.EVENT_DETAIL_ID)
    private EventDetail eventDetail;
    private BigDecimal price;
    private Integer quantity;
    @DateTimeFormat(pattern = StringConstant.DATE_TIME_FORMAT)
    private Calendar createAt;
    @Transient
    private String categoryIdTransient;
    @Transient
    private String eventDetailIdTransient;
    @Transient
    private Integer onSaleTransient;
    @Transient
    private Integer freeTransient;

    @OneToMany(mappedBy = TicketConstant.TICKET, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<BookingDetail>bookingDetails;

    @OneToMany(mappedBy = TicketConstant.TICKET, cascade = CascadeType.PERSIST)
    private List<TicketCode> ticketCodes;

    public Ticket() {
    }

    public Ticket(BigDecimal price, Integer quantity, Calendar createAt, String categoryIdTransient, String eventDetailIdTransient) {
        this.price = price;
        this.quantity = quantity;
        this.createAt = createAt;
        this.categoryIdTransient = categoryIdTransient;
        this.eventDetailIdTransient = eventDetailIdTransient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public EventDetail getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(EventDetail eventDetail) {
        this.eventDetail = eventDetail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Calendar getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Calendar createAt) {
        this.createAt = createAt;
    }

    public String getCategoryIdTransient() {
        return categoryIdTransient;
    }

    public void setCategoryIdTransient(String categoryIdTransient) {
        this.categoryIdTransient = categoryIdTransient;
    }

    public String getEventDetailIdTransient() {
        return eventDetailIdTransient;
    }

    public void setEventDetailIdTransient(String eventDetailIdTransient) {
        this.eventDetailIdTransient = eventDetailIdTransient;
    }

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public List<TicketCode> getTicketCodes() {
        return ticketCodes;
    }

    public void setTicketCodes(List<TicketCode> ticketCodes) {
        this.ticketCodes = ticketCodes;
    }

    public Integer getOnSaleTransient() {
        return onSaleTransient;
    }

    public void setOnSaleTransient(Integer onSaleTransient) {
        this.onSaleTransient = onSaleTransient;
    }

    public Integer getFreeTransient() {
        return freeTransient;
    }

    public void setFreeTransient(Integer freeTransient) {
        this.freeTransient = freeTransient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(category, ticket.category) &&
                Objects.equals(eventDetail, ticket.eventDetail) &&
                Objects.equals(price, ticket.price) &&
                Objects.equals(quantity, ticket.quantity) &&
                Objects.equals(createAt, ticket.createAt) &&
                Objects.equals(categoryIdTransient, ticket.categoryIdTransient) &&
                Objects.equals(eventDetailIdTransient, ticket.eventDetailIdTransient) &&
                Objects.equals(onSaleTransient, ticket.onSaleTransient) &&
                Objects.equals(freeTransient, ticket.freeTransient) &&
                Objects.equals(bookingDetails, ticket.bookingDetails) &&
                Objects.equals(ticketCodes, ticket.ticketCodes) &&
                Objects.equals(onSaleTransient, ticket.onSaleTransient) &&
                Objects.equals(freeTransient, ticket.freeTransient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, eventDetail, price, quantity, createAt, categoryIdTransient, eventDetailIdTransient, onSaleTransient, freeTransient, bookingDetails, ticketCodes);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", category=" + category +
                ", event=" + eventDetail +
                ", price=" + price +
                ", quantity=" + quantity +
                ", createAt=" + createAt +
                ", categoryIdTransient='" + categoryIdTransient + '\'' +
                ", eventIdTransient='" + eventDetailIdTransient + '\'' +
                ", bookingDetails=" + bookingDetails +
                ", ticketCodes=" + ticketCodes +
                ", onSaleTransient=" + onSaleTransient +
                ", freeTransient=" + freeTransient +
                '}';
    }
}
