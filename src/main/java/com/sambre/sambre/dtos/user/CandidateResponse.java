package com.sambre.sambre.dtos.user;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateResponse {
    private Long id;
    private String email;
    private String username;
    private String phone;
    private String userType; // "CANDIDATE" ou "COMPANY"
    private boolean enabled;
}