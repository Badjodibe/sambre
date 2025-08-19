package com.sambre.sambre.dtos.offers;

import com.sambre.sambre.entities.enumerations.JobOfferStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record JobOfferResponse(
        String jobOfferId,
        String title,
        String description,
        String jobType,
        String employmentMode,
        String location,
        List<String> requiredSkills,

        String experienceLevel,
        int minYearsExperience,
        String educationLevel,

        BigDecimal salaryMin,
        BigDecimal salaryMax,
        JobOfferStatus offerStatus,
        LocalDate applicationDeadline,
        int numberOfPositions,
        String languageRequirement,
        String benefits,
        String region,

        // 🔹 Référence à la Company
        String companyId,
        String companyName
) {
}
