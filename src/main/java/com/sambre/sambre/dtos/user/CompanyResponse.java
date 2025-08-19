package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.events.EventResponse;
import com.sambre.sambre.dtos.offers.JobOfferResponse;
import com.sambre.sambre.dtos.utils.SocialResponse;

import java.util.List;

public record CompanyResponse(
        String id,
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
        String fullName,

        // 🔹 Champs spécifiques à Company
        String sector,
        String name,
        String fiscalIdentificationNumber,
        String location,
        // 🔹 Collections liées
        List<JobOfferResponse> jobOffers,
        List<EventResponse> events
) {
}
