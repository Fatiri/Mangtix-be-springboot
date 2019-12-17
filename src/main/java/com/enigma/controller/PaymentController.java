package com.enigma.controller;

import com.enigma.entity.Payment;
import com.enigma.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment")
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayment() {
        return paymentService.getAllPayment();
    }

    @GetMapping("/payment/{paymentId}")
    public Payment getPaymentId(@PathVariable String paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @PutMapping("/payment")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @DeleteMapping("/payment/{paymentId}")
    public void deletePayment(@PathVariable String paymentId) {
        paymentService.deletePayment(paymentId);
    }
}
