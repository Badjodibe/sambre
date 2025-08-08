package com.sambre.sambre.entities.user;

import com.sambre.sambre.entities.events.Event;
import com.sambre.sambre.entities.offers.JobOffer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Company extends User{

    private String sector;

    //@NotBlank(message = "Le prénom ne peut pas être vide ou null")
    private String name;

    @Column(unique = true)
    //@NotBlank(message = "L'email ne peut pas être vide ou null")
    private String fiscalIdentificationNumber;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOffer> jobOffers;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;
    private String location;

}
