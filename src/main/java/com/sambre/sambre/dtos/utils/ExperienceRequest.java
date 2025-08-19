package com.sambre.sambre.dtos.utils;

import jakarta.validation.constraints.NotBlank;

public record ExperienceRequest(
        String id, // si modif d’expérience existante
        @NotBlank String title,
        @NotBlank String company,
        String startDate,  // format ISO: "2023-01-10"
        String endDate,    // peut être null si en cours
        String description
) {
}
