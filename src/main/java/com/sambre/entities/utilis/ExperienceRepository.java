package com.sambre.entities.utilis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, String> {
    // Trouver toutes les expériences par utilisateur
    Iterable<Experience> findByUserId(String userId);

    // Trouver les expériences par titre
    Iterable<Experience> findByTitle(String title);

    // Supprimer les expériences par utilisateur
    void deleteByUserId(String userId);

    // Compter les expériences par utilisateur
    long countByUserId(String userId);
}
