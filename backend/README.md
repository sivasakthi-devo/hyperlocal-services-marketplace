# Hyperlocal Services Marketplace - Backend

## Project Description

Backend REST API for the Hyperlocal Services Marketplace.

The system connects customers with local service providers for services such as plumbing, electrical work, cleaning, and appliance repair.

## Technology Stack

- Java 17
- Spring Boot 3.5.16
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

## Main Modules

- Authentication
- Users
- Service Categories
- Provider Profiles
- Provider Services
- Service Requests
- Bookings
- Payments
- Reviews

## Authentication

The backend uses JWT-based authentication.

### Signup

```text
POST /api/auth/signup

Categories
GET    /api/categories
POST   /api/categories
GET    /api/categories/{id}
PUT    /api/categories/{id}
DELETE /api/categories/{id}

Providers
GET    /api/providers
GET    /api/providers/{id}
POST   /api/providers/user/{userId}
PUT    /api/providers/{id}
DELETE /api/providers/{id}

Provider Services
GET    /api/provider-services
GET    /api/provider-services/{id}
GET    /api/provider-services/provider/{providerId}
GET    /api/provider-services/category/{categoryId}
POST   /api/provider-services/provider/{providerId}/category/{categoryId}
PUT    /api/provider-services/{id}
DELETE /api/provider-services/{id}

Service Requests
GET    /api/service-requests
GET    /api/service-requests/{id}
GET    /api/service-requests/customer/{customerId}
GET    /api/service-requests/category/{categoryId}
GET    /api/service-requests/status/{status}
POST   /api/service-requests/customer/{customerId}/category/{categoryId}
PUT    /api/service-requests/{id}/status
DELETE /api/service-requests/{id}


Bookings
GET    /api/bookings
GET    /api/bookings/{id}
GET    /api/bookings/request/{serviceRequestId}
GET    /api/bookings/provider/{providerId}
GET    /api/bookings/status/{status}
POST   /api/bookings/request/{serviceRequestId}/provider/{providerId}
PUT    /api/bookings/{id}/status
DELETE /api/bookings/{id}

Payments
GET    /api/payments
GET    /api/payments/{id}
GET    /api/payments/booking/{bookingId}
GET    /api/payments/status/{status}
POST   /api/payments/booking/{bookingId}
PUT    /api/payments/{id}/status
DELETE /api/payments/{id}

Reviews
GET    /api/reviews
GET    /api/reviews/{id}
GET    /api/reviews/booking/{bookingId}
GET    /api/reviews/provider/{providerId}
GET    /api/reviews/customer/{customerId}
POST   /api/reviews/booking/{bookingId}/customer/{customerId}/provider/{providerId}
PUT    /api/reviews/{id}
DELETE /api/reviews/{id}
