package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.utils.SocialRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CompanyRequest(
        @NotBlank(message = "Le prénom ne peut pas être vide")
        String firstname,

        @NotBlank(message = "Le nom ne peut pas être vide")
        String lastname,

        @Email(message = "Email invalide")
        @NotBlank(message = "L'email est obligatoire")
        String email,

        @NotBlank(message = "Le mot de passe est obligatoire")
        String password,

        String state,       // ex: ACTIVE
        String profile,     // ex: "Entreprise de recrutement IT"
        String tel,

        List<SocialRequest> socials, // réseaux sociaux de l’entreprise
        List<String> roles,          // ["ROLE_COMPANY"]

        // 🔹 Champs spécifiques à Company
        String sector,                 // secteur d’activité
        @NotBlank(message = "Le nom de l'entreprise est obligatoire")
        String name,
        @NotBlank(message = "Le NIF est obligatoire")
        String fiscalIdentificationNumber,
        String location                // localisation
) {
}
