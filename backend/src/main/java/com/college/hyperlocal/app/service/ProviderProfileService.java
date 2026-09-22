package com.college.hyperlocal.app.service;

import com.college.hyperlocal.app.model.entity.ProviderProfile;
import com.college.hyperlocal.app.repository.ProviderProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderProfileService {

    private final ProviderProfileRepository providerProfileRepository;

    public ProviderProfileService(
            ProviderProfileRepository providerProfileRepository) {
        this.providerProfileRepository = providerProfileRepository;
    }

    public ProviderProfile saveProfile(ProviderProfile profile) {
        return providerProfileRepository.save(profile);
    }

    public List<ProviderProfile> getAllProfiles() {
        return providerProfileRepository.findAll();
    }

    public Optional<ProviderProfile> findById(Long id) {
        return providerProfileRepository.findById(id);
    }

    public Optional<ProviderProfile> findByUserId(Long userId) {
        return providerProfileRepository.findByUserId(userId);
    }

    public boolean existsByUserId(Long userId) {
        return providerProfileRepository.existsByUserId(userId);
    }

    public void deleteProfile(Long id) {
        providerProfileRepository.deleteById(id);
    }
}