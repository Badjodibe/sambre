package com.sambre.sambre.dtos.offers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JobOfferResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String jobType; // ex: FULL_TIME, PART_TIME
    private String companyName;
    private String companyLogoUrl;
    private LocalDateTime postedAt;
    private LocalDateTime deadline;
}
