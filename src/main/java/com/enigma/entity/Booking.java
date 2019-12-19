package com.enigma.entity;
import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = StringConstant.TRX_BOOKING)
public class Booking {
    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    @DateTimeFormat(pattern = StringConstant.DATE_TIME_FORMAT)
    private Date bookDate;
    private BigDecimal totalPrice;
    private Boolean paymentStatus;

    @OneToMany(mappedBy = StringConstant.BOOKING, cascade = CascadeType.PERSIST)
    private List<BookingDetail> bookingDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = StringConstant.USER_ID)
    @JsonIgnore
    private User user;
    @Transient
    private String userIdTransient;

    public Booking() {
    }

    public Booking(Date bookDate, BigDecimal totalPrice, Boolean paymentStatus) {
        this.bookDate = bookDate;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<BookingDetail> getBookingDetailList() {
        return bookingDetailList;
    }

    public void setBookingDetailList(List<BookingDetail> bookingDetailList) {
        this.bookingDetailList = bookingDetailList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserIdTransient() {
        return userIdTransient;
    }

    public void setUserIdTransient(String userIdTransient) {
        this.userIdTransient = userIdTransient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(bookDate, booking.bookDate) &&
                Objects.equals(totalPrice, booking.totalPrice) &&
                Objects.equals(paymentStatus, booking.paymentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookDate, totalPrice, paymentStatus);
    }
}
