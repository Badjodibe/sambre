package com.sambre.sambre.dtos.user;


import com.sambre.sambre.dtos.events.EventDTO;
import com.sambre.sambre.dtos.offers.JobOfferDTO;
import com.sambre.sambre.dtos.utils.SocialDTO;

import java.util.List;

public record CompanyDTO(
        String id,
        String firstname,
        String lastname,
        String email,
        String state,
        String profile,
        String tel,
        List<SocialDTO> socials,
        List<String> roles,     // Liste des rôles en String
        String fullName,        // Champ dérivé optionnel
        String sector,
        String name,
        String fiscalIdentificationNumber,
        String location,
        List<JobOfferDTO> jobOffers  // DTOs légers pour éviter les cycles
        //List<EventDTO> events         // DTOs légers pour éviter les cycles
) {}
