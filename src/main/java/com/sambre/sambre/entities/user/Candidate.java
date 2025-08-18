package com.sambre.sambre.entities.user;

import com.sambre.sambre.entities.events.EventParticipant;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.entities.utils.Experience;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Candidate extends User{

    @OneToMany
    private List<Experience> experiences;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.PERSIST , orphanRemoval = true)
    private List<EventParticipant> registeredEvents;

}
