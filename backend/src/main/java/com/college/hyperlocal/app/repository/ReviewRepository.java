package com.college.hyperlocal.app.repository;

import com.college.hyperlocal.app.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByBookingId(Long bookingId);

    List<Review> findByProviderId(Long providerId);

    List<Review> findByCustomerId(Long customerId);

    boolean existsByBookingId(Long bookingId);
}