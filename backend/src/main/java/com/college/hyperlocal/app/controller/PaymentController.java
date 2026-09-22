package com.college.hyperlocal.app.controller;

import com.college.hyperlocal.app.dto.PaymentResponse;
import com.college.hyperlocal.app.model.entity.Booking;
import com.college.hyperlocal.app.model.entity.Payment;
import com.college.hyperlocal.app.service.BookingService;
import com.college.hyperlocal.app.service.PaymentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final BookingService bookingService;

    public PaymentController(
            PaymentService paymentService,
            BookingService bookingService) {

        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {

        List<PaymentResponse> response =
                paymentService.getAllPayments()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return paymentService.findById(id)
                .map(payment ->
                        ResponseEntity.ok(toResponse(payment)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentResponse> getPaymentByBooking(
            @PathVariable Long bookingId) {

        return paymentService.findByBookingId(bookingId)
                .map(payment ->
                        ResponseEntity.ok(toResponse(payment)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByStatus(
            @PathVariable String status) {

        List<PaymentResponse> response =
                paymentService.findByStatus(status)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentResponse> createPayment(
            @PathVariable Long bookingId,
            @RequestBody Payment request) {

        if (paymentService.existsByBookingId(bookingId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var booking = bookingService.findById(bookingId);

        if (booking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Booking bookingEntity = booking.get();

        request.setBooking(bookingEntity);
        request.setStatus("COMPLETED");

        Payment savedPayment =
                paymentService.savePayment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(savedPayment));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponse> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return paymentService.findById(id)
                .map(payment -> {

                    payment.setStatus(status);

                    Payment updatedPayment =
                            paymentService.savePayment(payment);

                    return ResponseEntity.ok(
                            toResponse(updatedPayment));
                })
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(
            @PathVariable Long id) {

        if (paymentService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        paymentService.deletePayment(id);

        return ResponseEntity.noContent().build();
    }

    private PaymentResponse toResponse(Payment payment) {

        return new PaymentResponse(
                payment.getId(),
                payment.getBooking().getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getPaidAt()
        );
    }
}