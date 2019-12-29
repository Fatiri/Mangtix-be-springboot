package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "trx_payment")
public class Payment {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    private BigDecimal totalPayment;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date paymentDate = new Date();
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    @Transient
    private String bookingIdTransient;

    public Payment() {
    }

    public Payment(BigDecimal totalPayment, Date paymentDate) {
        this.totalPayment = totalPayment;
        this.paymentDate = paymentDate;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getBookingIdTransient() {
        return bookingIdTransient;
    }

    public void setBookingIdTransient(String bookingIdTransient) {
        this.bookingIdTransient = bookingIdTransient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(totalPayment, payment.totalPayment) &&
                Objects.equals(paymentDate, payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPayment, paymentDate);
    }
}
