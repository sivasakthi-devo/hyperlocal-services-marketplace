package com.college.hyperlocal.app.controller;

import com.college.hyperlocal.app.dto.ServiceRequestResponse;
import com.college.hyperlocal.app.model.entity.ServiceCategory;
import com.college.hyperlocal.app.model.entity.ServiceRequest;
import com.college.hyperlocal.app.model.entity.User;
import com.college.hyperlocal.app.service.ServiceCategoryService;
import com.college.hyperlocal.app.service.ServiceRequestService;
import com.college.hyperlocal.app.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;
    private final UserService userService;
    private final ServiceCategoryService serviceCategoryService;

    public ServiceRequestController(
            ServiceRequestService serviceRequestService,
            UserService userService,
            ServiceCategoryService serviceCategoryService) {

        this.serviceRequestService = serviceRequestService;
        this.userService = userService;
        this.serviceCategoryService = serviceCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequestResponse>> getAllRequests() {

        List<ServiceRequestResponse> response =
                serviceRequestService.getAllRequests()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequestResponse> getRequestById(
            @PathVariable Long id) {

        return serviceRequestService.findById(id)
                .map(request ->
                        ResponseEntity.ok(toResponse(request)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceRequestResponse>> getRequestsByCustomer(
            @PathVariable Long customerId) {

        List<ServiceRequestResponse> response =
                serviceRequestService.findByCustomerId(customerId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ServiceRequestResponse>> getRequestsByCategory(
            @PathVariable Long categoryId) {

        List<ServiceRequestResponse> response =
                serviceRequestService.findByCategoryId(categoryId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ServiceRequestResponse>> getRequestsByStatus(
            @PathVariable String status) {

        List<ServiceRequestResponse> response =
                serviceRequestService.findByStatus(status)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/customer/{customerId}/category/{categoryId}")
    public ResponseEntity<ServiceRequestResponse> createRequest(
            @PathVariable Long customerId,
            @PathVariable Long categoryId,
            @RequestBody ServiceRequest request) {

        var customer = userService.findById(customerId);
        var category = serviceCategoryService.findById(categoryId);

        if (customer.isEmpty() || category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User customerUser = customer.get();
        ServiceCategory serviceCategory = category.get();

        request.setCustomer(customerUser);
        request.setCategory(serviceCategory);
        request.setStatus("PENDING");

        ServiceRequest savedRequest =
                serviceRequestService.saveRequest(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(savedRequest));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ServiceRequestResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return serviceRequestService.findById(id)
                .map(existingRequest -> {

                    existingRequest.setStatus(status);

                    ServiceRequest updatedRequest =
                            serviceRequestService.saveRequest(
                                    existingRequest);

                    return ResponseEntity.ok(
                            toResponse(updatedRequest));
                })
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(
            @PathVariable Long id) {

        if (serviceRequestService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        serviceRequestService.deleteRequest(id);

        return ResponseEntity.noContent().build();
    }

    private ServiceRequestResponse toResponse(
            ServiceRequest request) {

        return new ServiceRequestResponse(
                request.getId(),
                request.getCustomer().getId(),
                request.getCustomer().getName(),
                request.getCategory().getId(),
                request.getCategory().getName(),
                request.getIssueDescription(),
                request.getLocation(),
                request.getStatus(),
                request.getCreatedAt()
        );
    }
}