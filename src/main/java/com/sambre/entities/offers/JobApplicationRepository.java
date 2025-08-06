package com.sambre.entities.offers;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends CrudRepository<JobApplication, String> {

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByJobOfferId(Long jobOfferId);

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByCandidateId(Long candidateId);

    List<JobApplication> findByStatusIgnoreCase(String status);

    boolean existsByCandidateIdAndJobOfferId(Long candidateId, Long jobOfferId);

    long countByJobOfferId(Long jobOfferId);

    List<JobApplication> findByCandidateIdAndStatusIgnoreCase(Long candidateId, String status);

    List<JobApplication> findByCoverLetterContainingIgnoreCase(String keyword);

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findAll();

    List<JobApplication> findByJobOfferIdAndStatusIgnoreCase(Long jobOfferId, String status);
}
