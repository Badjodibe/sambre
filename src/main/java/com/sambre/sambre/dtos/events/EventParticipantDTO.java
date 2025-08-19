package com.sambre.sambre.dtos.events;



import com.sambre.sambre.dtos.user.CandidateDTO;

import java.time.LocalDateTime;

public record EventParticipantDTO(
        String eventParticipantId,
        CandidateDTO participant,  // DTO simplifié du candidat
        EventDTO event,            // DTO simplifié de l'événement
        LocalDateTime registrationDate
) {}

