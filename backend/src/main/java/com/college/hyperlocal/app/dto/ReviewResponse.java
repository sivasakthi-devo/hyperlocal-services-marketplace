package com.college.hyperlocal.app.dto;

import java.time.LocalDateTime;

public class ReviewResponse {

    private Long id;
    private Long bookingId;
    private Long customerId;
    private String customerName;
    private Long providerId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

    public ReviewResponse() {
    }

    public ReviewResponse(
            Long id,
            Long bookingId,
            Long customerId,
            String customerName,
            Long providerId,
            Integer rating,
            String comment,
            LocalDateTime createdAt) {

        this.id = id;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.providerId = providerId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getProviderId() {
        return providerId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}