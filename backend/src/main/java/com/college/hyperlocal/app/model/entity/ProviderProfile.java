package com.college.hyperlocal.app.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "provider_profiles")
public class ProviderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String phone;

    @Column(length = 500)
    private String bio;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean available = true;

    @Column(nullable = false)
    private Double rating = 0.0;

    public ProviderProfile() {
    }

    public ProviderProfile(
            User user,
            String phone,
            String bio,
            String location) {

        this.user = user;
        this.phone = phone;
        this.bio = bio;
        this.location = location;
        this.available = true;
        this.rating = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}