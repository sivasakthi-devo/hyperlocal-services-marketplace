package com.college.hyperlocal.app.repository;

import com.college.hyperlocal.app.model.entity.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderServiceRepository extends JpaRepository<ProviderService, Long> {

    List<ProviderService> findByProviderId(Long providerId);

    List<ProviderService> findByCategoryId(Long categoryId);

    boolean existsByProviderIdAndCategoryId(Long providerId, Long categoryId);
}