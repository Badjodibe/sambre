package com.sambre.entities.utilis;

import com.sambre.entities.user.Candidate;
import com.sambre.entities.user.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating; // 1 à 5
    private String comment;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Candidate candidate;

    private LocalDateTime createdAt;
}
