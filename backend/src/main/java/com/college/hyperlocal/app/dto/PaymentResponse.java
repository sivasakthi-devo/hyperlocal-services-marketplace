package com.college.hyperlocal.app.dto;

import java.time.LocalDateTime;

public class PaymentResponse {

    private Long id;
    private Long bookingId;
    private Double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paidAt;

    public PaymentResponse() {
    }

    public PaymentResponse(
            Long id,
            Long bookingId,
            Double amount,
            String paymentMethod,
            String status,
            LocalDateTime paidAt) {

        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paidAt = paidAt;
    }

    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }
}