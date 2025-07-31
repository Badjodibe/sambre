package com.sambre.dtos.security;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticateResponse {
    private String token;
}