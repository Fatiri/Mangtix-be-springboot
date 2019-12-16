package com.enigma.services.impl;

import com.enigma.constanta.StringConstant;
import com.enigma.entity.Payment;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.PaymentRepository;
import com.enigma.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentId(String paymentId) {
        if (!paymentRepository.findById(paymentId).isPresent()) {
            throw new NotFoundException(String.format(StringConstant.ID_PAYMENT_NOT_FOUND, paymentId));
        }
        return paymentRepository.findById(paymentId).get();
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(String paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }
}
