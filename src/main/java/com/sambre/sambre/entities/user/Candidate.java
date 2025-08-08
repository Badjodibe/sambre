package com.sambre.sambre.entities.user;

import com.sambre.sambre.entities.events.EventParticipant;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.entities.utilis.Experience;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
public class Candidate extends User{

    @OneToMany
    private List<Experience> experiences;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipant> registeredEvents;


}
