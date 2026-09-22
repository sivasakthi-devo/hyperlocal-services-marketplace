package com.college.hyperlocal.app.dto;

public class ProviderProfileResponse {

    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String bio;
    private String location;
    private Boolean available;
    private Double rating;

    public ProviderProfileResponse() {
    }

    public ProviderProfileResponse(
            Long id,
            Long userId,
            String name,
            String email,
            String phone,
            String bio,
            String location,
            Boolean available,
            Double rating) {

        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
        this.location = location;
        this.available = available;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Double getRating() {
        return rating;
    }
}