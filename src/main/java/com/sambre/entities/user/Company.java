package com.sambre.entities.user;

import com.sambre.entities.events.Event;
import com.sambre.entities.offers.JobOffer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Company extends User{

    private String sector;

    //@NotBlank(message = "Le prénom ne peut pas être vide ou null")
    private String firstname;

    @Column(unique = true)
    //@NotBlank(message = "L'email ne peut pas être vide ou null")
    private String fiscalIdentificationNumber;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOffer> jobOffers;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

}
