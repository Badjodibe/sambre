package com.sambre.entities.utilis;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //@NotBlank(message = "Le nom ne peut pas être vide ou null")
    private String title;

    //@NotBlank(message = "Le prénom ne peut pas être vide ou null")
    private String entity;

    @Column(unique = true)
    //@NotBlank(message = "L'email ne peut pas être vide ou null")
    private String description;

    //@NotBlank(message = "Le mot de passe ne peut pas être vide ou null")
    private Date start;

    private Date end;

    private String emplacement;

}
