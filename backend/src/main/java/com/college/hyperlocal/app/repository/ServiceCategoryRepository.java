package com.college.hyperlocal.app.repository;

import com.college.hyperlocal.app.model.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCategoryRepository
        extends JpaRepository<ServiceCategory, Long> {

    Optional<ServiceCategory> findByName(String name);

    boolean existsByName(String name);
}