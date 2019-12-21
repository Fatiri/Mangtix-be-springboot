package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.Booking;
import com.enigma.entity.BookingDetail;
import com.enigma.entity.Payment;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.PaymentRepository;
import com.enigma.services.BookingService;
import com.enigma.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    BookingService bookingService;

    @Override
    public Payment getPaymentById(String id) {
        if (!paymentRepository.findById(id).isPresent()) {
            throw new NotFoundException(String.format(MessageConstant.ID_PAYMENT_NOT_FOUND, id));
        }
        return paymentRepository.findById(id).get();
    }

    @Override
    public Payment savePayment(Payment payment) {
        Booking booking = bookingService.getBookingById(payment.getBookingIdTransient());
        payment.setBooking(booking);
        BigDecimal totalPrice = new BigDecimal(0);
        for (BookingDetail bookingDetail: booking.getBookingDetailList()) {
            totalPrice = totalPrice.add(bookingDetail.getSubtotal());
        }
        System.out.println(totalPrice);
        payment.setTotalPayment(totalPrice);
        booking.setPaymentStatus(true);
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }
}
