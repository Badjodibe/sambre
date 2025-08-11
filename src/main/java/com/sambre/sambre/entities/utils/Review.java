package com.sambre.sambre.entities.utils;

import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.entities.user.Company;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int rating; // 1 à 5
    private String comment;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Candidate candidate;

    private LocalDateTime createdAt;
}
