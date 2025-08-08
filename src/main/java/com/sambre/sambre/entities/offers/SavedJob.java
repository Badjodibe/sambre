package com.sambre.sambre.entities.offers;

import com.sambre.sambre.entities.user.Candidate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
public class SavedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private JobOffer jobOffer;

    private LocalDateTime savedAt;
}

