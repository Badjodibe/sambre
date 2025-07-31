package com.sambre.entities.utilis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int years;
    private String name;
    private String level;
}
