package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.utils.ExperienceRequest;
import com.sambre.sambre.dtos.utils.SocialRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CandidateRequest(
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
        String profile,     // ex: "Software Engineer"
        String tel,

        List<SocialRequest> socials, // DTO pour réseaux sociaux
        List<String> roles,          // ex: ["ROLE_USER", "ROLE_CANDIDATE"]
        List<ExperienceRequest> experiences // Expériences du candidat
) {
}
