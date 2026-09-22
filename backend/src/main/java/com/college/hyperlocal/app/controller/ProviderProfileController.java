package com.college.hyperlocal.app.controller;

import com.college.hyperlocal.app.dto.ProviderProfileResponse;
import com.college.hyperlocal.app.model.entity.ProviderProfile;
import com.college.hyperlocal.app.service.ProviderProfileService;
import com.college.hyperlocal.app.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderProfileController {

    private final ProviderProfileService providerProfileService;
    private final UserService userService;

    public ProviderProfileController(
            ProviderProfileService providerProfileService,
            UserService userService) {

        this.providerProfileService = providerProfileService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderProfileResponse>> getAllProviders() {

        List<ProviderProfileResponse> response =
                providerProfileService.getAllProfiles()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderProfileResponse> getProviderById(
            @PathVariable Long id) {

        return providerProfileService.findById(id)
                .map(profile ->
                        ResponseEntity.ok(toResponse(profile))
                )
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProviderProfileResponse> getProviderByUserId(
            @PathVariable Long userId) {

        return providerProfileService.findByUserId(userId)
                .map(profile ->
                        ResponseEntity.ok(toResponse(profile))
                )
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ProviderProfileResponse> createProviderProfile(
            @PathVariable Long userId,
            @RequestBody ProviderProfile profile) {

        if (providerProfileService.existsByUserId(userId)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        return userService.findById(userId)
                .map(user -> {

                    profile.setUser(user);

                    ProviderProfile savedProfile =
                            providerProfileService.saveProfile(profile);

                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(toResponse(savedProfile));
                })
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderProfileResponse> updateProviderProfile(
            @PathVariable Long id,
            @RequestBody ProviderProfile profile) {

        return providerProfileService.findById(id)
                .map(existingProfile -> {

                    existingProfile.setPhone(profile.getPhone());
                    existingProfile.setBio(profile.getBio());
                    existingProfile.setLocation(profile.getLocation());
                    existingProfile.setAvailable(profile.getAvailable());

                    ProviderProfile updatedProfile =
                            providerProfileService.saveProfile(existingProfile);

                    return ResponseEntity.ok(
                            toResponse(updatedProfile)
                    );
                })
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProviderProfile(
            @PathVariable Long id) {

        if (providerProfileService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        providerProfileService.deleteProfile(id);

        return ResponseEntity.noContent().build();
    }

    private ProviderProfileResponse toResponse(
            ProviderProfile profile) {

        return new ProviderProfileResponse(
                profile.getId(),
                profile.getUser().getId(),
                profile.getUser().getName(),
                profile.getUser().getEmail(),
                profile.getPhone(),
                profile.getBio(),
                profile.getLocation(),
                profile.getAvailable(),
                profile.getRating()
        );
    }
}