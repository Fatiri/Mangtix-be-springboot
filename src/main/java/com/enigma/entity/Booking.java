package com.enigma.entity;
import com.enigma.constanta.BookingConstant;
import com.enigma.constanta.StringConstant;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = BookingConstant.TRX_BOOKING)
public class Booking {
    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    @DateTimeFormat(pattern = StringConstant.DATE_TIME_FORMAT)
    private Date bookDate;
    private Boolean paymentStatus;

    @OneToMany(mappedBy = BookingConstant.BOOKING, cascade = CascadeType.ALL)
    private List<BookingDetail> bookingDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = StringConstant.USER_ID)
    private User user;
    @Transient
    private String userIdTransient;

    public Booking() {
    }

    public Booking(Date bookDate, Boolean paymentStatus){
        this.bookDate = bookDate;
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
                Objects.equals(paymentStatus, booking.paymentStatus) &&
                Objects.equals(bookingDetailList, booking.bookingDetailList) &&
                Objects.equals(user, booking.user) &&
                Objects.equals(userIdTransient, booking.userIdTransient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookDate, paymentStatus, bookingDetailList, user, userIdTransient);
    }
}
