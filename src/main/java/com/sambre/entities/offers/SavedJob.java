package com.sambre.entities.offers;

import com.sambre.entities.user.Candidate;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SavedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private JobOffer jobOffer;

    private LocalDateTime savedAt;
}

