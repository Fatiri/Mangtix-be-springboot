package com.enigma.entity;
import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mst_booking")
public class Booking {
    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookDate;
    private BigDecimal totalPrice;
    private Boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Transient
    private String userIdTransient;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
    private List<BookingDetail> bookingDetailList = new ArrayList<>();

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

    public List<BookingDetail> getBookingDetailList() {
        return bookingDetailList;
    }

    public void setBookingDetailList(List<BookingDetail> bookingDetailList) {
        this.bookingDetailList = bookingDetailList;
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
