package com.sambre.entities.utilis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends CrudRepository<Interview, String> {
    Iterable<Interview> findByCandidateId(String candidateId);
}
