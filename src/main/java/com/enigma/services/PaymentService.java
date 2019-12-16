package com.enigma.services;

import com.enigma.entity.Payment;

import java.util.List;

public interface PaymentService {

    public Payment getPaymentId(String paymentId);
    public Payment savePayment(Payment payment);
    public void deletePayment(String paymentId);
    public List<Payment> getAllPayment();
}
