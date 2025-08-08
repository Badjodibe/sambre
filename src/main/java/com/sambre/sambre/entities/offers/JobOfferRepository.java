package com.sambre.sambre.entities.offers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobOfferRepository extends CrudRepository<JobOffer, String> {
    @EntityGraph(attributePaths = {"company"})
    List<JobOffer> findAll(); // Évite les N+1 sur company

    @EntityGraph(attributePaths = {"company"})
    Optional<JobOffer> findById(String id);

    List<JobOffer> findByTitleContainingIgnoreCase(String keyword);

    @Query("SELECT j FROM JobOffer j WHERE j.company.id = :companyId")
    List<JobOffer> findByCompanyId(@Param("companyId") Long companyId);

//    @Query("SELECT j FROM JobOffer j ORDER BY j.createdAt DESC")
//    List<JobOffer> findAllOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT j FROM JobOffer j JOIN FETCH j.company WHERE j.id = :id")
    Optional<JobOffer> findWithCompanyById(@Param("id") String id);

    // 1. Trouver les offres par région
    List<JobOffer> findByRegionIgnoreCase(String region);

    // 2. Trouver les offres par type de contrat
    List<JobOffer> findByjobTypeIgnoreCase(String contractType);
}
