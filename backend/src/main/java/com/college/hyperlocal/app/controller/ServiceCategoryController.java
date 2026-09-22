package com.college.hyperlocal.app.controller;

import com.college.hyperlocal.app.dto.ApiResponse;
import com.college.hyperlocal.app.model.entity.ServiceCategory;
import com.college.hyperlocal.app.service.ServiceCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;

    public ServiceCategoryController(
            ServiceCategoryService serviceCategoryService) {
        this.serviceCategoryService = serviceCategoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceCategory>> createCategory(
            @RequestBody ServiceCategory category) {

        try {
            ServiceCategory savedCategory =
                    serviceCategoryService.saveCategory(category);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            savedCategory,
                            "Category created successfully"
                    )
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            false,
                            null,
                            e.getMessage()
                    )
            );
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceCategory>>> getAllCategories() {

        List<ServiceCategory> categories =
                serviceCategoryService.getAllCategories();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        categories,
                        "Categories retrieved successfully"
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceCategory>> getCategoryById(
            @PathVariable Long id) {

        return serviceCategoryService.getCategoryById(id)
                .map(category ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        true,
                                        category,
                                        "Category retrieved successfully"
                                )
                        )
                )
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<ServiceCategory>> getCategoryByName(
            @PathVariable String name) {

        return serviceCategoryService.getCategoryByName(name)
                .map(category ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        true,
                                        category,
                                        "Category retrieved successfully"
                                )
                        )
                )
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(
            @PathVariable Long id) {

        if (serviceCategoryService.getCategoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        serviceCategoryService.deleteCategory(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        null,
                        "Category deleted successfully"
                )
        );
    }
}