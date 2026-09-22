    package com.college.hyperlocal.app.repository;

import com.college.hyperlocal.app.model.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByCustomerId(Long customerId);

    List<ServiceRequest> findByCategoryId(Long categoryId);

    List<ServiceRequest> findByStatus(String status);
}