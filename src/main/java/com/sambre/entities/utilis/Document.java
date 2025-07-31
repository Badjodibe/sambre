package com.sambre.entities.utilis;

import com.sambre.entities.user.Candidate;
import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // pdf, docx, etc.
    private String url;

    @ManyToOne
    private Candidate candidate;
}
