package com.sambre.sambre.dtos.user;

import com.sambre.sambre.dtos.events.EventParticipantResponse;
import com.sambre.sambre.dtos.offers.JobApplicationResponse;
import com.sambre.sambre.dtos.utils.ExperienceResponse;
import com.sambre.sambre.dtos.utils.SocialResponse;

import java.util.List;

public record CandidateResponse(
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
        List<ExperienceResponse> experiences,
        List<JobApplicationResponse> jobApplications,
        List<EventParticipantResponse> registeredEvents
) {
}
