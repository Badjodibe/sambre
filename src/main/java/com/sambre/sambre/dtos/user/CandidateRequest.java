package com.sambre.sambre.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CandidateRequest {
    private String id;
    //@NotBlank(message = "Le nom ne peut pas être vide ou null")
    private String lastname;
    //@NotBlank(message = "Le prénom ne peut pas être vide ou null")
    private String firstname;
    //@NotBlank(message = "L'email ne peut pas être vide ou null")
    private String email;
    //@NotBlank(message = "Le mot de passe ne peut pas être vide ou null")
    private String password;
    private String state;
    private String profile;
    private String tel;
}
