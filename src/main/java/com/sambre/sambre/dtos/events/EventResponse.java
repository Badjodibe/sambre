package com.sambre.sambre.dtos.events;

import java.time.LocalDateTime;

public record EventResponse(
        String eventId,
        String title,
        String description,
        String location,
        LocalDateTime startDate,
        LocalDateTime endDate,

        // 🔹 Informations sur l'organisateur
        String organizerId,
        String organizerName,

        // 🔹 Infos sur les participants (optionnel)
        int participantCount
) {
}
