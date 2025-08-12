package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.events.EventParticipantDTO;
import com.sambre.sambre.dtos.offers.JobApplicationDTO;
import com.sambre.sambre.dtos.utils.ExperienceDTO;
import com.sambre.sambre.dtos.utils.SocialDTO;

import java.util.List;

public record CandidateDTO(
        String id,
        String firstname,
        String lastname,
        String email,
        String state,
        String profile,
        String tel,
        List<SocialDTO> socials,
        List<String> roles,     // rôles en String lisibles
        String fullName,        // champ dérivé
        List<ExperienceDTO> experiences,
        List<JobApplicationDTO> jobApplications,
        List<EventParticipantDTO> registeredEvents
) {}

