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
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    //@NotBlank(message = "Le nom ne peut pas être vide ou null")
    private String name;
    //@NotBlank(message = "Le prénom ne peut pas être vide ou null")
    private String url;
}
