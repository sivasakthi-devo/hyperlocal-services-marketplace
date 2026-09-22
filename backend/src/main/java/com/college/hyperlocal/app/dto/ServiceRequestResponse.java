package com.college.hyperlocal.app.dto;

import java.time.LocalDateTime;

public class ServiceRequestResponse {

    private Long id;
    private Long customerId;
    private String customerName;
    private Long categoryId;
    private String categoryName;
    private String issueDescription;
    private String location;
    private String status;
    private LocalDateTime createdAt;

    public ServiceRequestResponse() {
    }

    public ServiceRequestResponse(
            Long id,
            Long customerId,
            String customerName,
            Long categoryId,
            String categoryName,
            String issueDescription,
            String location,
            String status,
            LocalDateTime createdAt) {

        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.issueDescription = issueDescription;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}