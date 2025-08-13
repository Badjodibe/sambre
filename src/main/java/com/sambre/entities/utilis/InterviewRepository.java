package com.sambre.entities.utilis;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends CrudRepository<Interview, String> {
    @Query("SELECT i FROM Interview i LEFT JOIN FETCH i.jobApplication j JOIN FETCH j.candidate c WHERE c.id = :candidateId")
    Iterable<Interview> findByJobApplication(@Param("candidateId") String candidateId);
}
