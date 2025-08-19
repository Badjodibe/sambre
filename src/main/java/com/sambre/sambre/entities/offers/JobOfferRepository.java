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
    List<JobOffer> findAll(); // Évite le N+1 sur company

    @EntityGraph(attributePaths = {"company"})
    Optional<JobOffer> findByJobOfferId(String jobOfferId);

    List<JobOffer> findByTitleContainingIgnoreCase(String keyword);

    @Query("SELECT j FROM JobOffer j WHERE j.company.userId = :companyId")
    List<JobOffer> findByCompanyId(@Param("companyId") String companyId);

    @Query("SELECT j FROM JobOffer j JOIN FETCH j.company WHERE j.jobOfferId = :id")
    Optional<JobOffer> findWithCompanyByJobOfferId(@Param("id") String id);

    // 1. Trouver les offres par région
    List<JobOffer> findByRegionIgnoreCase(String region);

    // 2. Trouver les offres par type de contrat
    List<JobOffer> findByJobTypeIgnoreCase(String jobType);
}
