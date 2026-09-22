package com.college.hyperlocal.app.service;

import com.college.hyperlocal.app.model.entity.Booking;
import com.college.hyperlocal.app.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public Optional<Booking> findByServiceRequestId(Long serviceRequestId) {
        return bookingRepository.findByServiceRequestId(serviceRequestId);
    }

    public List<Booking> findByProviderId(Long providerId) {
        return bookingRepository.findByProviderId(providerId);
    }

    public List<Booking> findByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    public boolean existsByServiceRequestId(Long serviceRequestId) {
        return bookingRepository.existsByServiceRequestId(serviceRequestId);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}