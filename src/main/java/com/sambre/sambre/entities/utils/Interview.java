package com.sambre.sambre.entities.utils;

import com.sambre.sambre.entities.offers.JobApplication;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime scheduledDate;
    private String location; // ou lien visio

    @ManyToOne
    private JobApplication jobApplication;
}

