package com.college.hyperlocal.app.dto;

public class ProviderServiceResponse {

    private Long id;
    private Long providerId;
    private String providerName;
    private String providerLocation;
    private Long categoryId;
    private String categoryName;
    private Double servicePrice;
    private String description;

    public ProviderServiceResponse() {
    }

    public ProviderServiceResponse(
            Long id,
            Long providerId,
            String providerName,
            String providerLocation,
            Long categoryId,
            String categoryName,
            Double servicePrice,
            String description) {

        this.id = id;
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerLocation = providerLocation;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.servicePrice = servicePrice;
        this.description = description;
    }

    public Long getId() {
        return id;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public String getDescription() {
        return description;
    }
}