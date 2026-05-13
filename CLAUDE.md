# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build
./mvnw clean install          # Windows: mvnw.cmd clean install

# Run (requires PostgreSQL — Docker Compose starts it automatically)
./mvnw spring-boot:run

# Tests
./mvnw test
./mvnw test -Dtest=ClassName          # single class
./mvnw test -Dtest=ClassName#method   # single method

# Package
./mvnw package -DskipTests

# Database (Spring Boot Docker Compose starts this automatically on run)
docker compose up -d
```

Java 21, Spring Boot 4.0.6, Maven.

## Architecture

**EventSphere** is an event management web service (meetups, local events) built with a server-side MVC stack: Spring MVC + Thymeleaf + HTMX. Standard three-layer structure: Controller → Service → Repository.

**Tech stack:**
- ORM: Spring Data JPA / Hibernate with Flyway migrations
- Frontend: Thymeleaf + HTMX (htmx-spring-boot-thymeleaf v5.1.0) + thymeleaf-extras-springsecurity6
- Auth: Spring Security 6
- API docs: SpringDoc OpenAPI 3.0.2
- Utilities: Lombok, Spring DevTools
- Testing: JUnit 5, Spring Test, Spring Security Test
- External APIs: Weather (OpenWeather/Yandex) and Maps for location selection

**Database:** PostgreSQL via `compose.yaml` (user: `myuser`, password: `secret`, db: `mydatabase`, port: 5432). Spring Boot's Docker Compose support starts it automatically when running the app locally. Migrations go in `src/main/resources/db/migration/`.

## Domain Model

All entities extend `BaseEntity` (provides `id`, `createdAt`, `updatedAt`). JPA Auditing is enabled.

**Package layout under `io.github.ussesent.entity`:**

| Package   | Key classes |
|-----------|-------------|
| `common`  | `BaseEntity` (abstract base) |
| `auth`    | `User`, `Profile` (1:1 with User), `Role` enum |
| `event`   | `Event` (core), `Attachment`, `WeatherCache`, `EventStatus` enum, `AccessType` enum |
| `catalog` | `Category` (name is an i18n translation key), `Location` (lat/lon as `BigDecimal` precision 10,7) |
| `social`  | `EventRegistration`, `Comment`, `Invitation`, `RegistrationStatus` enum, `InvitationStatus` enum |
| `system`  | `Notification` (stub) |

**Core relationships:**
- `User` → `Profile` (1:1), owns many `Event`s, has many `EventRegistration`s, `Comment`s, and `Invitation`s
- `Event` → `User` (owner), `Category`, `Location`, optional banner `Attachment`, many `EventRegistration`s, `Comment`s
- `EventRegistration` has a unique constraint on `(event_id, user_id)`; supports waitlist via `RegistrationStatus` (CONFIRMED, AWAITING_APPROVAL, CANCELED, REJECTED, WAITLISTED)
- `Invitation` links a sender `User`, receiver `User`, and `Event`; status: PENDING, ACCEPTED, DECLINED
- `Comment` may have an optional `Attachment` (image); content stored as LOB
- `Attachment` tracks `storageKey` (unique), `contentType`, `size`, and uploader `User`
- `WeatherCache` caches weather data keyed on `(city, targetTime)`

**Access control:** `Event.accessType` is `OPEN`, `OPEN_WITH_MODERATION`, or `PRIVATE`.

## Project Status

The entity layer is complete. Controller, Service, DTO, Repository, and Exception layers have empty package directories ready for implementation. The `templates/` and `static/` frontend directories are also empty. There are no Flyway migrations yet.
