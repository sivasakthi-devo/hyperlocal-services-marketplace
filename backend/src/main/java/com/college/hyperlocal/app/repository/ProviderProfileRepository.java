package com.college.hyperlocal.app.repository;

import com.college.hyperlocal.app.model.entity.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderProfileRepository extends JpaRepository<ProviderProfile, Long> {

    Optional<ProviderProfile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}