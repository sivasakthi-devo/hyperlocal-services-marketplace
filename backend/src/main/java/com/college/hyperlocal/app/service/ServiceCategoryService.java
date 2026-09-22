package com.college.hyperlocal.app.service;

import com.college.hyperlocal.app.model.entity.ServiceCategory;
import com.college.hyperlocal.app.repository.ServiceCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;

    public ServiceCategoryService(
            ServiceCategoryRepository serviceCategoryRepository) {
        this.serviceCategoryRepository = serviceCategoryRepository;
    }

    public ServiceCategory saveCategory(ServiceCategory category) {

        if (serviceCategoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category already exists");
        }

        return serviceCategoryRepository.save(category);
    }

    public List<ServiceCategory> getAllCategories() {
        return serviceCategoryRepository.findAll();
    }

    public Optional<ServiceCategory> getCategoryById(Long id) {
        return serviceCategoryRepository.findById(id);
    }

    public Optional<ServiceCategory> findById(Long id) {
        return serviceCategoryRepository.findById(id);
    }

    public Optional<ServiceCategory> getCategoryByName(String name) {
        return serviceCategoryRepository.findByName(name);
    }

    public void deleteCategory(Long id) {
        serviceCategoryRepository.deleteById(id);
    }
}