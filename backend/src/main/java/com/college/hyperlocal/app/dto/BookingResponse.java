package com.college.hyperlocal.app.dto;

import java.time.LocalDateTime;

public class BookingResponse {

    private Long id;
    private Long serviceRequestId;
    private Long providerId;
    private String providerName;
    private String providerLocation;
    private String status;
    private LocalDateTime createdAt;

    public BookingResponse() {
    }

    public BookingResponse(
            Long id,
            Long serviceRequestId,
            Long providerId,
            String providerName,
            String providerLocation,
            String status,
            LocalDateTime createdAt) {

        this.id = id;
        this.serviceRequestId = serviceRequestId;
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerLocation = providerLocation;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getServiceRequestId() {
        return serviceRequestId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getProviderLocation() {
        return providerLocation;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}