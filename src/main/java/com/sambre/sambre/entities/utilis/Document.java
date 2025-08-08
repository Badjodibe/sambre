package com.sambre.sambre.entities.utilis;

import com.sambre.sambre.entities.user.Candidate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Entity
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String type; // pdf, docx, etc.
    private String url;

    @ManyToOne
    private Candidate candidate;
}
