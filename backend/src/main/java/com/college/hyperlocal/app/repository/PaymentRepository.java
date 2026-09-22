package com.college.hyperlocal.app.repository;

import com.college.hyperlocal.app.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    List<Payment> findByStatus(String status);

    boolean existsByBookingId(Long bookingId);
}