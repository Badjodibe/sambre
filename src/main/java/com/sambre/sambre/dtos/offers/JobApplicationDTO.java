package com.sambre.sambre.dtos.offers;


import com.sambre.sambre.entities.enumerations.ApplicationStatus;
import java.time.LocalDateTime;

public record JobApplicationDTO(
        String id,
        String motivation,
        LocalDateTime applicationDate, // côté sortie
        String candidateId,
        String jobOfferId,
        ApplicationStatus status
) {}
