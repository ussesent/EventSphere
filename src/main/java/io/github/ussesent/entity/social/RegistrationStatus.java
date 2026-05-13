package io.github.ussesent.entity.social;

public enum RegistrationStatus {
    CONFIRMED,
    AWAITING_APPROVAL, // for OPEN_WITH_MODERATION events
    CANCELED,
    REJECTED,
    WAITLISTED // There are no seats, standing in line
}