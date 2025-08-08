package com.sambre.sambre.dtos.security;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {
    private LocalDateTime createAt;
    private LocalDateTime expireAt;
    private LocalDateTime validateAt;
    private String token;
    private String usersId;
}
