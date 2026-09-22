package com.college.hyperlocal.app.service;

import com.college.hyperlocal.app.model.entity.Payment;
import com.college.hyperlocal.app.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    public Optional<Payment> findByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public List<Payment> findByStatus(String status) {
        return paymentRepository.findByStatus(status);
    }

    public boolean existsByBookingId(Long bookingId) {
        return paymentRepository.existsByBookingId(bookingId);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}