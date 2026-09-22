package com.college.hyperlocal.app.service;

import com.college.hyperlocal.app.model.entity.Review;
import com.college.hyperlocal.app.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public Optional<Review> findByBookingId(Long bookingId) {
        return reviewRepository.findByBookingId(bookingId);
    }

    public List<Review> findByProviderId(Long providerId) {
        return reviewRepository.findByProviderId(providerId);
    }

    public List<Review> findByCustomerId(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    public boolean existsByBookingId(Long bookingId) {
        return reviewRepository.existsByBookingId(bookingId);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}