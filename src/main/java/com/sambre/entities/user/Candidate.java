package com.sambre.entities.user;

import com.sambre.entities.events.EventParticipant;
import com.sambre.entities.offers.JobApplication;
import com.sambre.entities.utilis.Experience;
import com.sambre.entities.utilis.Social;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Candidate extends User{

    private List<Experience> experiences;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipant> registeredEvents;

}
