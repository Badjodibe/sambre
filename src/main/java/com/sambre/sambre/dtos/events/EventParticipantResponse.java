package com.sambre.sambre.dtos.events;

public record EventParticipantResponse(
        String eventParticipantId,
        String eventId,
        String eventName,
        String registrationDate
) {
}
