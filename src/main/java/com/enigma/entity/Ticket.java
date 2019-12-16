package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

@Entity(name = "mst_ticket")
public class Ticket {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private BigDecimal price;
    private Integer quantity;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Calendar createAt;
    @Transient
    private String categoryIdTransient;
    @Transient
    private String eventIdTransient;

    public Ticket(BigDecimal price, Integer quantity, Calendar createAt, String categoryIdTransient, String eventIdTransient) {
        this.price = price;
        this.quantity = quantity;
        this.createAt = createAt;
        this.categoryIdTransient = categoryIdTransient;
        this.eventIdTransient = eventIdTransient;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    public String getEventIdTransient() {
        return eventIdTransient;
    }

    public void setEventIdTransient(String eventIdTransient) {
        this.eventIdTransient = eventIdTransient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(category, ticket.category) &&
                Objects.equals(event, ticket.event) &&
                Objects.equals(price, ticket.price) &&
                Objects.equals(quantity, ticket.quantity) &&
                Objects.equals(createAt, ticket.createAt) &&
                Objects.equals(categoryIdTransient, ticket.categoryIdTransient) &&
                Objects.equals(eventIdTransient, ticket.eventIdTransient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, event, price, quantity, createAt, categoryIdTransient, eventIdTransient);
    }
}
