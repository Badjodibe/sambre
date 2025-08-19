package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.utils.SocialRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserRequest(
        @NotBlank(message = "Le prénom ne peut pas être vide")
        String firstname,

        @NotBlank(message = "Le nom ne peut pas être vide")
        String lastname,

        @Email(message = "Email invalide")
        @NotBlank(message = "L'email est obligatoire")
        String email,

        @NotBlank(message = "Le mot de passe est obligatoire")
        String password,

        String state,       // ex: ACTIVE, INACTIVE
        String profile,     // ex: "Software Engineer"
        String tel,         // numéro de téléphone

        List<SocialRequest> socials, // DTO pour réseaux sociaux

        List<String> roles   // ["ROLE_USER", "ROLE_ADMIN"]
) {
}
