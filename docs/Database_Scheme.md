# Database Schema

## 1. USERS

| Column | Type | Constraints |
|---|---|---|
| user_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(150) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | NOT NULL |
| phone | VARCHAR(15) | NOT NULL |
| role | VARCHAR(20) | NOT NULL |
| address | TEXT | |
| created_at | TIMESTAMP | NOT NULL |

---

## 2. PROVIDER_PROFILES

| Column | Type | Constraints |
|---|---|---|
| provider_id | BIGINT | PRIMARY KEY, FOREIGN KEY → USERS.user_id |
| business_name | VARCHAR(150) | NOT NULL |
| experience_years | INT | |
| description | TEXT | |
| service_area | VARCHAR(150) | |
| availability_status | VARCHAR(20) | NOT NULL |
| rating | DECIMAL(3,2) | DEFAULT 0.00 |

---

## 3. SERVICE_CATEGORIES

| Column | Type | Constraints |
|---|---|---|
| category_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| category_name | VARCHAR(100) | UNIQUE, NOT NULL |
| description | TEXT | |

---

## 4. PROVIDER_SERVICES

| Column | Type | Constraints |
|---|---|---|
| provider_service_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| provider_id | BIGINT | FOREIGN KEY → PROVIDER_PROFILES.provider_id |
| category_id | BIGINT | FOREIGN KEY → SERVICE_CATEGORIES.category_id |
| service_description | TEXT | |
| base_price | DECIMAL(10,2) | |

---

## 5. SERVICE_REQUESTS

| Column | Type | Constraints |
|---|---|---|
| request_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| customer_id | BIGINT | FOREIGN KEY → USERS.user_id |
| category_id | BIGINT | FOREIGN KEY → SERVICE_CATEGORIES.category_id |
| description | TEXT | NOT NULL |
| location | VARCHAR(255) | NOT NULL |
| preferred_date | DATE | |
| status | VARCHAR(30) | NOT NULL |
| created_at | TIMESTAMP | NOT NULL |

---

## 6. BOOKINGS

| Column | Type | Constraints |
|---|---|---|
| booking_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| request_id | BIGINT | FOREIGN KEY → SERVICE_REQUESTS.request_id |
| provider_id | BIGINT | FOREIGN KEY → PROVIDER_PROFILES.provider_id |
| booking_date | DATE | NOT NULL |
| status | VARCHAR(30) | NOT NULL |
| final_price | DECIMAL(10,2) | |
| completed_at | TIMESTAMP | |

---

## 7. PAYMENTS

| Column | Type | Constraints |
|---|---|---|
| payment_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| booking_id | BIGINT | FOREIGN KEY → BOOKINGS.booking_id |
| amount | DECIMAL(10,2) | NOT NULL |
| payment_method | VARCHAR(30) | |
| payment_status | VARCHAR(30) | NOT NULL |
| transaction_reference | VARCHAR(150) | UNIQUE |
| paid_at | TIMESTAMP | |

---

## 8. REVIEWS

| Column | Type | Constraints |
|---|---|---|
| review_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| booking_id | BIGINT | FOREIGN KEY → BOOKINGS.booking_id |
| customer_id | BIGINT | FOREIGN KEY → USERS.user_id |
| provider_id | BIGINT | FOREIGN KEY → PROVIDER_PROFILES.provider_id |
| rating | INT | NOT NULL |
| comment | TEXT | |
| created_at | TIMESTAMP | NOT NULL |

---

## Relationships

- One USER can have one PROVIDER_PROFILE.
- One PROVIDER_PROFILE can provide multiple services.
- One SERVICE_CATEGORY can contain multiple provider services.
- One CUSTOMER can create multiple SERVICE_REQUESTS.
- One SERVICE_CATEGORY can have multiple SERVICE_REQUESTS.
- One SERVICE_REQUEST can result in one BOOKING.
- One PROVIDER_PROFILE can receive multiple BOOKINGS.
- One BOOKING can have one PAYMENT.
- One completed BOOKING can have one REVIEW.
- A PROVIDER_PROFILE can receive multiple REVIEWS.

## Main Business Flow

Customer → Service Request → Provider Matching → Booking → Payment → Service Completion → Review