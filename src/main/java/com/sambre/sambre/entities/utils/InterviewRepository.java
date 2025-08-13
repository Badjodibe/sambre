package com.sambre.sambre.entities.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InterviewRepository extends CrudRepository<Interview, String> {

    // Récupérer toutes les interviews pour une JobApplication donnée
    List<Interview> findByJobApplicationId(String jobApplicationId);

    // Récupérer toutes les interviews programmées après une certaine date
    List<Interview> findByScheduledDateAfter(LocalDateTime date);

    // Récupérer toutes les interviews programmées avant une certaine date
    List<Interview> findByScheduledDateBefore(LocalDateTime date);

    // Récupérer toutes les interviews programmées à un emplacement donné
    List<Interview> findByLocationContainingIgnoreCase(String location);
}
