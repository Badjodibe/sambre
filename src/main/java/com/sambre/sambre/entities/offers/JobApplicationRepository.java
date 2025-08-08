package com.sambre.sambre.entities.offers;

import com.sambre.sambre.entities.enumerations.ApplicationStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends CrudRepository<JobApplication, String> {

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByJobOfferId(String jobOfferId);

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByCandidateId(String candidateId);

    List<JobApplication> findByStatus(ApplicationStatus status);

    boolean existsByCandidateIdAndJobOfferId(String candidateId, String jobOfferId);

    long countByJobOfferId(String jobOfferId);

    List<JobApplication> findByCandidateIdAndStatus(String candidateId, ApplicationStatus status);

    //List<JobApplication> findByCoverLetterContainingIgnoreCase(String keyword);

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findAll();

    List<JobApplication> findByJobOfferIdAndStatus(String jobOfferId, ApplicationStatus status);
}
