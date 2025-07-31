package com.sambre.dtos.user;

import com.sambre.dtos.offers.JobOfferResponse;
import com.sambre.entities.offers.JobOffer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyResponse {
    private Long id;
    private String username; // hérité de User
    private String email;    // hérité de User
    private String phoneNumber; // hérité de User
    private String address;     // hérité de User
    private String profileImage;

    private String sector;
    private String firstname; // nom du représentant
    private String fiscalIdentificationNumber;

    // Champs supplémentaires utiles dans le contexte d'une plateforme de recrutement
    private String description;
    private String website;
    private Integer numberOfEmployees;
    private String location;

    // Liste des offres publiées (simplifiée)
    private List<JobOfferResponse> jobOffers;

}

