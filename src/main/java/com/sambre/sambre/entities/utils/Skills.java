package com.sambre.sambre.entities.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int years;
    private String name;
    private String level;
}
