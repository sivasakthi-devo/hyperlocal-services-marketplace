package com.college.hyperlocal.app.controller;

import com.college.hyperlocal.app.dto.ProviderServiceResponse;
import com.college.hyperlocal.app.model.entity.ProviderProfile;
import com.college.hyperlocal.app.model.entity.ProviderService;
import com.college.hyperlocal.app.model.entity.ServiceCategory;
import com.college.hyperlocal.app.service.ProviderProfileService;
import com.college.hyperlocal.app.service.ProviderServiceService;
import com.college.hyperlocal.app.service.ServiceCategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider-services")
public class ProviderServiceController {

    private final ProviderServiceService providerServiceService;
    private final ProviderProfileService providerProfileService;
    private final ServiceCategoryService serviceCategoryService;

    public ProviderServiceController(
            ProviderServiceService providerServiceService,
            ProviderProfileService providerProfileService,
            ServiceCategoryService serviceCategoryService) {

        this.providerServiceService = providerServiceService;
        this.providerProfileService = providerProfileService;
        this.serviceCategoryService = serviceCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderServiceResponse>> getAllProviderServices() {

        List<ProviderServiceResponse> response =
                providerServiceService.getAllProviderServices()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderServiceResponse> getById(
            @PathVariable Long id) {

        return providerServiceService.findById(id)
                .map(service ->
                        ResponseEntity.ok(toResponse(service)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ProviderServiceResponse>> getByProvider(
            @PathVariable Long providerId) {

        List<ProviderServiceResponse> response =
                providerServiceService.findByProviderId(providerId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProviderServiceResponse>> getByCategory(
            @PathVariable Long categoryId) {

        List<ProviderServiceResponse> response =
                providerServiceService.findByCategoryId(categoryId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/provider/{providerId}/category/{categoryId}")
    public ResponseEntity<ProviderServiceResponse> addService(
            @PathVariable Long providerId,
            @PathVariable Long categoryId,
            @RequestBody ProviderService request) {

        if (providerServiceService
                .existsByProviderIdAndCategoryId(
                        providerId,
                        categoryId)) {

            return ResponseEntity.status(
                    HttpStatus.CONFLICT
            ).build();
        }

        var provider =
                providerProfileService.findById(providerId);

        var category =
                serviceCategoryService.findById(categoryId);

        if (provider.isEmpty() || category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProviderProfile providerProfile = provider.get();
        ServiceCategory serviceCategory = category.get();

        request.setProvider(providerProfile);
        request.setCategory(serviceCategory);

        ProviderService savedService =
                providerServiceService.saveProviderService(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(savedService));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderServiceResponse> updateService(
            @PathVariable Long id,
            @RequestBody ProviderService request) {

        return providerServiceService.findById(id)
                .map(existingService -> {

                    existingService.setServicePrice(
                            request.getServicePrice()
                    );

                    existingService.setDescription(
                            request.getDescription()
                    );

                    ProviderService updatedService =
                            providerServiceService
                                    .saveProviderService(
                                            existingService
                                    );

                    return ResponseEntity.ok(
                            toResponse(updatedService)
                    );
                })
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(
            @PathVariable Long id) {

        if (providerServiceService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        providerServiceService.deleteProviderService(id);

        return ResponseEntity.noContent().build();
    }

    private ProviderServiceResponse toResponse(
            ProviderService providerService) {

        return new ProviderServiceResponse(
                providerService.getId(),
                providerService.getProvider().getId(),
                providerService.getProvider().getUser().getName(),
                providerService.getProvider().getLocation(),
                providerService.getCategory().getId(),
                providerService.getCategory().getName(),
                providerService.getServicePrice(),
                providerService.getDescription()
        );
    }
}