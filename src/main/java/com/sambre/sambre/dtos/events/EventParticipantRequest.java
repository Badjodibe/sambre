package com.sambre.sambre.dtos.events;

import jakarta.validation.constraints.NotBlank;

public record EventParticipantRequest(
        @NotBlank(message = "L'identifiant de l'événement est obligatoire")
        String eventId
) {
}
