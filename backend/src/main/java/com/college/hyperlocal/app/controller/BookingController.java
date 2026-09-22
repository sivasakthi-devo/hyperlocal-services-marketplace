package com.college.hyperlocal.app.controller;

import com.college.hyperlocal.app.dto.BookingResponse;
import com.college.hyperlocal.app.model.entity.Booking;
import com.college.hyperlocal.app.model.entity.ProviderProfile;
import com.college.hyperlocal.app.model.entity.ServiceRequest;
import com.college.hyperlocal.app.service.BookingService;
import com.college.hyperlocal.app.service.ProviderProfileService;
import com.college.hyperlocal.app.service.ServiceRequestService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final ServiceRequestService serviceRequestService;
    private final ProviderProfileService providerProfileService;

    public BookingController(
            BookingService bookingService,
            ServiceRequestService serviceRequestService,
            ProviderProfileService providerProfileService) {

        this.bookingService = bookingService;
        this.serviceRequestService = serviceRequestService;
        this.providerProfileService = providerProfileService;
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {

        List<BookingResponse> response =
                bookingService.getAllBookings()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(
            @PathVariable Long id) {

        return bookingService.findById(id)
                .map(booking ->
                        ResponseEntity.ok(toResponse(booking)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @GetMapping("/request/{serviceRequestId}")
    public ResponseEntity<BookingResponse> getBookingByRequest(
            @PathVariable Long serviceRequestId) {

        return bookingService
                .findByServiceRequestId(serviceRequestId)
                .map(booking ->
                        ResponseEntity.ok(toResponse(booking)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByProvider(
            @PathVariable Long providerId) {

        List<BookingResponse> response =
                bookingService.findByProviderId(providerId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BookingResponse>> getBookingsByStatus(
            @PathVariable String status) {

        List<BookingResponse> response =
                bookingService.findByStatus(status)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/request/{serviceRequestId}/provider/{providerId}")
    public ResponseEntity<BookingResponse> createBooking(
            @PathVariable Long serviceRequestId,
            @PathVariable Long providerId) {

        if (bookingService.existsByServiceRequestId(serviceRequestId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var serviceRequest =
                serviceRequestService.findById(serviceRequestId);

        var provider =
                providerProfileService.findById(providerId);

        if (serviceRequest.isEmpty() || provider.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ServiceRequest request = serviceRequest.get();
        ProviderProfile providerProfile = provider.get();

        if (!Boolean.TRUE.equals(providerProfile.getAvailable())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Booking booking = new Booking(
                request,
                providerProfile,
                "CONFIRMED"
        );

        Booking savedBooking =
                bookingService.saveBooking(booking);

        request.setStatus("CONFIRMED");
        serviceRequestService.saveRequest(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(savedBooking));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponse> updateBookingStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return bookingService.findById(id)
                .map(booking -> {

                    booking.setStatus(status);

                    Booking updatedBooking =
                            bookingService.saveBooking(booking);

                    return ResponseEntity.ok(
                            toResponse(updatedBooking));
                })
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable Long id) {

        if (bookingService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bookingService.deleteBooking(id);

        return ResponseEntity.noContent().build();
    }

    private BookingResponse toResponse(Booking booking) {

        return new BookingResponse(
                booking.getId(),
                booking.getServiceRequest().getId(),
                booking.getProvider().getId(),
                booking.getProvider().getUser().getName(),
                booking.getProvider().getLocation(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
    }
}