package com.sambre.sambre.dtos.offers;

import jakarta.validation.constraints.NotBlank;

public record JobApplicationRequest(
        @NotBlank(message = "La motivation est obligatoire")
        String motivation,

        @NotBlank(message = "L'identifiant de l'offre est obligatoire")
        String jobOfferId
) {
}
