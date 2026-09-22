package com.college.hyperlocal.app.service;

import com.college.hyperlocal.app.model.entity.ProviderService;
import com.college.hyperlocal.app.repository.ProviderServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceService {

    private final ProviderServiceRepository providerServiceRepository;

    public ProviderServiceService(
            ProviderServiceRepository providerServiceRepository) {
        this.providerServiceRepository = providerServiceRepository;
    }

    public ProviderService saveProviderService(ProviderService providerService) {
        return providerServiceRepository.save(providerService);
    }

    public List<ProviderService> getAllProviderServices() {
        return providerServiceRepository.findAll();
    }

    public Optional<ProviderService> findById(Long id) {
        return providerServiceRepository.findById(id);
    }

    public List<ProviderService> findByProviderId(Long providerId) {
        return providerServiceRepository.findByProviderId(providerId);
    }

    public List<ProviderService> findByCategoryId(Long categoryId) {
        return providerServiceRepository.findByCategoryId(categoryId);
    }

    public boolean existsByProviderIdAndCategoryId(
            Long providerId,
            Long categoryId) {

        return providerServiceRepository
                .existsByProviderIdAndCategoryId(providerId, categoryId);
    }

    public void deleteProviderService(Long id) {
        providerServiceRepository.deleteById(id);
    }
}