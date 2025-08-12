package com.sambre.sambre.entities.offers;

import com.sambre.sambre.entities.user.Candidate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

