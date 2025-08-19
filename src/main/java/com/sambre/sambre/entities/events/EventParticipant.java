package com.sambre.sambre.entities.events;

import com.sambre.sambre.entities.user.Candidate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
public class EventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String eventParticipantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    private LocalDateTime registrationDate = LocalDateTime.now();
    @ManyToOne
    private Candidate participant;
}

