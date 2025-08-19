package com.sambre.sambre.dtos.events;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventRequest(
        @NotBlank(message = "Le titre est obligatoire")
        String title,

        @NotBlank(message = "La description est obligatoire")
        String description,

        @NotBlank(message = "La localisation est obligatoire")
        String location,

        @NotNull(message = "La date de début est obligatoire")
        LocalDateTime startDate,

        @NotNull(message = "La date de fin est obligatoire")
        LocalDateTime endDate
) {
}
