package com.sambre.sambre.services.offer;

import com.sambre.sambre.entities.offers.JobOffer;
import com.sambre.sambre.entities.offers.JobOfferRepository;
import com.sambre.sambre.mapper.offer.JobOfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    /** 🔹 Récupérer toutes les offres */
    public List<JobOffer> getAll() {
        return jobOfferRepository.findAll();
    }

    /** 🔹 Récupérer une offre par ID */
    public Optional<JobOffer> getById(String id) {
        return jobOfferRepository.findById(id);
    }

    /** 🔹 Récupérer une offre avec la société */
    public Optional<JobOffer> getWithCompanyById(String id) {
        return jobOfferRepository.findWithCompanyById(id);
    }

    /** 🔹 Recherche par titre */
    public List<JobOffer> searchByTitle(String keyword) {
        return jobOfferRepository.findByTitleContainingIgnoreCase(keyword);
    }

    /** 🔹 Recherche par ID société */
    public List<JobOffer> getByCompanyId(Long companyId) {
        return jobOfferRepository.findByCompanyId(companyId);
    }

    /** 🔹 Recherche par région */
    public List<JobOffer> getByRegion(String region) {
        return jobOfferRepository.findByRegionIgnoreCase(region);
    }

    /** 🔹 Recherche par type de contrat */
    public List<JobOffer> getByJobType(String contractType) {
        return jobOfferRepository.findByjobTypeIgnoreCase(contractType);
    }

    /** 🔹 Créer ou mettre à jour une offre */
    public JobOffer save(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    /** 🔹 Supprimer une offre */
    public boolean deleteById(String id) {
        if (jobOfferRepository.existsById(id)) {
            jobOfferRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

