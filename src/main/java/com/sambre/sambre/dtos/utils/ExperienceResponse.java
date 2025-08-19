package com.sambre.sambre.dtos.utils;

public record ExperienceResponse(
        String id,
        String title,
        String company,
        String startDate,
        String endDate,
        String description
) {
}
