package com.sambre.entities.utilis;

import com.sambre.entities.offers.JobApplication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime scheduledDate;
    private String location; // ou lien visio

    @ManyToOne
    private JobApplication jobApplication;
}

