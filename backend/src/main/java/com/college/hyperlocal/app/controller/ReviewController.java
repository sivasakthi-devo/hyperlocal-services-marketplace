package com.college.hyperlocal.app.controller;

import com.college.hyperlocal.app.dto.ReviewResponse;
import com.college.hyperlocal.app.model.entity.Booking;
import com.college.hyperlocal.app.model.entity.ProviderProfile;
import com.college.hyperlocal.app.model.entity.Review;
import com.college.hyperlocal.app.model.entity.User;
import com.college.hyperlocal.app.service.BookingService;
import com.college.hyperlocal.app.service.ProviderProfileService;
import com.college.hyperlocal.app.service.ReviewService;
import com.college.hyperlocal.app.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookingService bookingService;
    private final UserService userService;
    private final ProviderProfileService providerProfileService;

    public ReviewController(
            ReviewService reviewService,
            BookingService bookingService,
            UserService userService,
            ProviderProfileService providerProfileService) {

        this.reviewService = reviewService;
        this.bookingService = bookingService;
        this.userService = userService;
        this.providerProfileService = providerProfileService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {

        List<ReviewResponse> response =
                reviewService.getAllReviews()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(
            @PathVariable Long id) {

        return reviewService.findById(id)
                .map(review -> ResponseEntity.ok(toResponse(review)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<ReviewResponse> getReviewByBooking(
            @PathVariable Long bookingId) {

        return reviewService.findByBookingId(bookingId)
                .map(review -> ResponseEntity.ok(toResponse(review)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByProvider(
            @PathVariable Long providerId) {

        List<ReviewResponse> response =
                reviewService.findByProviderId(providerId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByCustomer(
            @PathVariable Long customerId) {

        List<ReviewResponse> response =
                reviewService.findByCustomerId(customerId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/booking/{bookingId}/customer/{customerId}/provider/{providerId}")
    public ResponseEntity<ReviewResponse> createReview(
            @PathVariable Long bookingId,
            @PathVariable Long customerId,
            @PathVariable Long providerId,
            @RequestBody Review request) {

        if (reviewService.existsByBookingId(bookingId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var booking = bookingService.findById(bookingId);
        var customer = userService.findById(customerId);
        var provider = providerProfileService.findById(providerId);

        if (booking.isEmpty() ||
                customer.isEmpty() ||
                provider.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        if (request.getRating() == null ||
                request.getRating() < 1 ||
                request.getRating() > 5) {

            return ResponseEntity.badRequest().build();
        }

        Booking bookingEntity = booking.get();
        User customerUser = customer.get();
        ProviderProfile providerProfile = provider.get();

        request.setBooking(bookingEntity);
        request.setCustomer(customerUser);
        request.setProvider(providerProfile);

        Review savedReview = reviewService.saveReview(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(savedReview));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long id,
            @RequestBody Review request) {

        return reviewService.findById(id)
                .map(existingReview -> {

                    existingReview.setRating(request.getRating());
                    existingReview.setComment(request.getComment());

                    Review updatedReview =
                            reviewService.saveReview(existingReview);

                    return ResponseEntity.ok(
                            toResponse(updatedReview)
                    );
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long id) {

        if (reviewService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        reviewService.deleteReview(id);

        return ResponseEntity.noContent().build();
    }

    private ReviewResponse toResponse(Review review) {

        return new ReviewResponse(
                review.getId(),
                review.getBooking().getId(),
                review.getCustomer().getId(),
                review.getCustomer().getName(),
                review.getProvider().getId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }
}