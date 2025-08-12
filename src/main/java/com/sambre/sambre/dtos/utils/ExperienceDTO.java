package com.sambre.sambre.dtos.utils;


import java.time.LocalDate;

public record ExperienceDTO(
        String id,
        String title,
        String companyName,
        LocalDate startDate,
        LocalDate endDate,
        String description
) {}

