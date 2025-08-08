package com.sambre.sambre.services.offer;

import com.sambre.sambre.dtos.offers.JobOfferResponse;
import com.sambre.sambre.entities.offers.JobOfferRepository;
import com.sambre.sambre.mapper.offer.JobOfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferMapper jobOfferMapper;

    // ✅ 1. Trouver toutes les offres (avec @EntityGraph sur company)
    public List<JobOfferResponse> findAll() {
        return jobOfferRepository.findAll()
                .stream()
                .map(jobOfferMapper::toJobResponse)
                .collect(Collectors.toList());
    }

    // ✅ 2. Trouver une offre par ID (avec @EntityGraph sur company)
    public Optional<JobOfferResponse> findById(String id) {
        return jobOfferRepository.findById(id)
                .map(jobOfferMapper::toJobResponse);
    }

    // ✅ 3. Recherche par mot-clé dans le titre
    public List<JobOfferResponse> searchByTitle(String keyword) {
        return jobOfferRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(jobOfferMapper::toJobResponse)
                .collect(Collectors.toList());
    }

    // ✅ 4. Trouver les offres d'une entreprise
    public List<JobOfferResponse> findByCompanyId(Long companyId) {
        return jobOfferRepository.findByCompanyId(companyId)
                .stream()
                .map(jobOfferMapper::toJobResponse)
                .collect(Collectors.toList());
    }

//    // ✅ 5. Offres récentes (ex: top 5 dernières offres)
//    public List<JobOfferResponse> findTopNByCreatedAt(int limit) {
//        Pageable pageable = PageRequest.of(0, limit);
//        return jobOfferRepository.findAllOrderByCreatedAtDesc(pageable)
//                .stream()
//                .map(jobOfferMapper::toJobResponse)
//                .collect(Collectors.toList());
//    }

    // ✅ 6. Trouver une offre avec les infos complètes de l'entreprise
    public Optional<JobOfferResponse> findWithCompanyById(String id) {
        return jobOfferRepository.findWithCompanyById(id)
                .map(jobOfferMapper::toJobResponse);
    }

    // ✅ 7. Trouver les offres par région
    public List<JobOfferResponse> findByRegion(String region) {
        return jobOfferRepository.findByRegionIgnoreCase(region)
                .stream()
                .map(jobOfferMapper::toJobResponse)
                .collect(Collectors.toList());
    }

    // ✅ 8. Trouver les offres par type de contrat
    public List<JobOfferResponse> findByContractType(String contractType) {
        return jobOfferRepository.findByjobTypeIgnoreCase(contractType)
                .stream()
                .map(jobOfferMapper::toJobResponse)
                .collect(Collectors.toList());
    }


}
