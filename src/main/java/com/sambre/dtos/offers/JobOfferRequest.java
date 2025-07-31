package com.sambre.dtos.offers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JobOfferRequest {
    // Informations générales
    private Long companyId;
    private String title;
    private String description;
    private String jobType; // Ex: "Full-time", "Part-time", "Contract", "Freelance", "Internship"
    private String employmentMode; // Ex: "Remote", "Onsite", "Hybrid"
    private String location;

    // Qualifications
    private List<String> requiredSkills;
    private String experienceLevel; // Ex: "Junior", "Mid-level", "Senior"
    private int minYearsExperience;
    private String educationLevel; // Ex: "Bachelor's", "Master's", "PhD"

    // Budget et rémunération
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String currency; // Ex: "USD", "EUR"
    private boolean isHourly; // Si c’est un tarif horaire (freelance)

    // Détails supplémentaires
    private LocalDate applicationDeadline;
    private int numberOfPositions;
    private String languageRequirement; // Ex: "Français courant", "Anglais professionnel"
    private String benefits; // Avantages proposés (mutuelle, tickets restau...)
    // Liens et contact
    private String companyWebsite;
    private String contactEmail;


}
