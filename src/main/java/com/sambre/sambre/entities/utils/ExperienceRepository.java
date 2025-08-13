package com.sambre.sambre.entities.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, String> {

    // Récupérer toutes les expériences par titre
    List<Experience> findByTitleContainingIgnoreCase(String title);

    // Récupérer toutes les expériences par entité (entreprise/organisation)
    List<Experience> findByEntityContainingIgnoreCase(String entity);

    // Récupérer toutes les expériences par emplacement
    List<Experience> findByEmplacementContainingIgnoreCase(String emplacement);

    // Récupérer les expériences actives (sans date de fin)
    List<Experience> findByEndDateIsNull();
}
