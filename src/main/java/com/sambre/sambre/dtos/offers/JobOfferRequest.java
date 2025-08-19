package com.sambre.sambre.dtos.offers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record JobOfferRequest(
        @NotBlank(message = "Le titre est obligatoire")
        String title,

        @NotBlank(message = "La description est obligatoire")
        String description,

        @NotBlank(message = "Le type de job est obligatoire")
        String jobType, // "Full-time", "Part-time", "Contract", "Freelance", "Internship"

        @NotBlank(message = "Le mode de travail est obligatoire")
        String employmentMode, // "Remote", "Onsite", "Hybrid"

        @NotBlank(message = "La localisation est obligatoire")
        String location,

        @NotNull(message = "Les compétences requises doivent être précisées")
        List<String> requiredSkills,

        @NotBlank(message = "Le niveau d'expérience est obligatoire")
        String experienceLevel, // "Junior", "Mid-level", "Senior"

        int minYearsExperience,

        @NotBlank(message = "Le niveau d'étude est obligatoire")
        String educationLevel, // "Bachelor's", "Master's", "PhD"

        @NotNull(message = "Le salaire minimum est obligatoire")
        BigDecimal salaryMin,

        @NotNull(message = "Le salaire maximum est obligatoire")
        BigDecimal salaryMax,

        @NotNull(message = "La date limite de candidature est obligatoire")
        LocalDate applicationDeadline,

        int numberOfPositions,

        String languageRequirement,
        String benefits,
        String region
) {
}
