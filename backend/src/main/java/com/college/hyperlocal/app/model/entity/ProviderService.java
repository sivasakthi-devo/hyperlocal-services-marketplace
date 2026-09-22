package com.college.hyperlocal.app.model.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "provider_services",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"provider_id", "category_id"}
                )
        }
)
public class ProviderService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private ProviderProfile provider;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ServiceCategory category;

    @Column(nullable = false)
    private Double servicePrice;

    @Column(length = 500)
    private String description;

    public ProviderService() {
    }

    public ProviderService(
            ProviderProfile provider,
            ServiceCategory category,
            Double servicePrice,
            String description) {

        this.provider = provider;
        this.category = category;
        this.servicePrice = servicePrice;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProviderProfile getProvider() {
        return provider;
    }

    public void setProvider(ProviderProfile provider) {
        this.provider = provider;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}