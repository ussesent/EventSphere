package io.github.ussesent.entity.social;

public enum RegistrationStatus {
    // waiting
    PENDING,    // Public events only: The user has submitted a request and is awaiting moderator approval.
    INVITED,    // Private events only: the owner invited the user, but the user has not yet responded

    // success
    CONFIRMED,

    // Refusal (User-side)
    CANCELED,   // The user deleted their registration (previously CONFIRMED/PENDING)
    DECLINED,   // Private events only: The user clicked "Decline Invitation"

    // Refusal (Admin/System-side)
    REJECTED,   // The admin has prohibited participation
    WAITLISTED // There are no seats, standing in line
}