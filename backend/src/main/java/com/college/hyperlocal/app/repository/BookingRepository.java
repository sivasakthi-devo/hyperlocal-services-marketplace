package com.college.hyperlocal.app.repository;

import com.college.hyperlocal.app.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByServiceRequestId(Long serviceRequestId);

    List<Booking> findByProviderId(Long providerId);

    List<Booking> findByStatus(String status);

    boolean existsByServiceRequestId(Long serviceRequestId);
}