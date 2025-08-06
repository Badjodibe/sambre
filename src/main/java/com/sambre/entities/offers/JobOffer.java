package com.sambre.entities.offers;

import com.sambre.entities.enumerations.JobOfferStatus;
import com.sambre.entities.user.Company;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
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

    @Enumerated(EnumType.STRING)
    private JobOfferStatus offerStatus;
    // Détails supplémentaires
    private LocalDate applicationDeadline;
    private int numberOfPositions;
    private String languageRequirement; //
    private String benefits; // Avantages proposés (mutuelle, tickets restau...)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications;

}
