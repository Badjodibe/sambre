package com.sambre.sambre.dtos.events;


import com.sambre.sambre.dtos.user.CompanyDTO;

import java.time.LocalDateTime;
import java.util.List;

public record EventDTO(
        String id,
        String title,
        String description,
        String location,
        LocalDateTime startDate,
        LocalDateTime endDate,
        CompanyDTO organizer,// DTO léger pour éviter de charger toute la Company
        List<EventParticipantDTO> participants
) {}

