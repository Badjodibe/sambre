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
public class Social{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    //@NotBlank(message = "Le nom ne peut pas être vide ou null")
    private String name;
    //@NotBlank(message = "Le prénom ne peut pas être vide ou null")
    private String url;
}
