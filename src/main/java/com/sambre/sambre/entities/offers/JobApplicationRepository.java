package com.sambre.sambre.entities.offers;

import com.sambre.sambre.entities.enumerations.ApplicationStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends CrudRepository<JobApplication, String> {

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByJobOfferJobOfferId(String jobOfferId);

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByCandidateUserId(String candidateId);

    List<JobApplication> findByStatus(ApplicationStatus status);

    boolean existsByCandidateUserIdAndJobOfferJobOfferId(String candidateId, String jobOfferId);

    long countByJobOfferJobOfferId(String jobOfferId);

    List<JobApplication> findByCandidateUserIdAndStatus(String candidateId, ApplicationStatus status);

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findAll();

    List<JobApplication> findByJobOfferJobOfferIdAndStatus(String jobOfferId, ApplicationStatus status);
}
