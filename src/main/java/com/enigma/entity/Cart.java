package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = StringConstant.MST_CART)
public class Cart {
    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    @Transient
    private String ticketIdTrancient;
    @ManyToOne
    @JoinColumn(name = StringConstant.TICKET_ID)
    @JsonIgnore
    private Ticket ticket;
    private Integer quantity;
    private BigDecimal subTotal;
    @Transient
    private String userIdTrancient;
    @ManyToOne
    @JoinColumn(name = StringConstant.USER_ID)
    @JsonIgnore
    private User user;

    public Cart() {
    }

    public Cart(String ticketIdTrancient, String userIdTrancient, Integer quantity, BigDecimal subTotal) {
        this.ticketIdTrancient = ticketIdTrancient;
        this.userIdTrancient = userIdTrancient;
        this.subTotal = subTotal;
        this.quantity = quantity;
    }

    public String getTicketIdTrancient() {
        return ticketIdTrancient;
    }

    public void setTicketIdTrancient(String ticketIdTrancient) {
        this.ticketIdTrancient = ticketIdTrancient;
    }

    public String getUserIdTrancient() {
        return userIdTrancient;
    }

    public void setUserIdTrancient(String userIdTrancient) {
        this.userIdTrancient = userIdTrancient;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(ticket, cart.ticket) &&
                Objects.equals(quantity, cart.quantity) &&
                Objects.equals(subTotal, cart.subTotal) &&
                Objects.equals(user, cart.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket, quantity, subTotal, user);
    }
}
