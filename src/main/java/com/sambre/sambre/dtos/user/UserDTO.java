package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.utils.SocialDTO;

import java.util.List;

public record UserDTO(
        String id,
        String firstname,
        String lastname,
        String email,
        String state,
        String profile,
        String tel,
        List<SocialDTO> socials,
        List<String> roles,     // roles en String lisibles
        String fullName         // champ dérivé (optionnel)
) {
}
