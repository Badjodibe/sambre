package com.sambre.entities.offers;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends CrudRepository<JobApplication, Long> {

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByJobOfferId(Long jobOfferId);

    @EntityGraph(attributePaths = {"candidate", "jobOffer"})
    List<JobApplication> findByCandidateId(Long candidateId);
}
