package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.utils.SocialResponse;

import java.util.List;

public record UserResponse(String id,
                           String firstname,
                           String lastname,
                           String email,
                           String state,
                           String profile,
                           String tel,
                           List<SocialResponse> socials,
                           List<String> roles,
                           boolean accountLocked,
                           boolean enabled,
                           String fullName // champ dérivé
                            ) {
}
