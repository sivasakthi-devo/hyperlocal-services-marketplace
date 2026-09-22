package com.college.hyperlocal.app.service;

import com.college.hyperlocal.app.model.entity.ServiceRequest;
import com.college.hyperlocal.app.repository.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestService(
            ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public ServiceRequest saveRequest(ServiceRequest request) {
        return serviceRequestRepository.save(request);
    }

    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    public Optional<ServiceRequest> findById(Long id) {
        return serviceRequestRepository.findById(id);
    }

    public List<ServiceRequest> findByCustomerId(Long customerId) {
        return serviceRequestRepository.findByCustomerId(customerId);
    }

    public List<ServiceRequest> findByCategoryId(Long categoryId) {
        return serviceRequestRepository.findByCategoryId(categoryId);
    }

    public List<ServiceRequest> findByStatus(String status) {
        return serviceRequestRepository.findByStatus(status);
    }

    public void deleteRequest(Long id) {
        serviceRequestRepository.deleteById(id);
    }
}