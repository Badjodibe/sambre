package com.sambre.entities.utilis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, String> {
    // Trouver toutes les expériences par utilisateur
//    Iterable<Experience> findByUserId(String userId);

    // Trouver les expériences par titre
    Iterable<Experience> findByTitle(String title);

    // Supprimer les expériences par titre
    void deleteByTitle(String title);

    // Compter les expériences par titre
    long countByTitle(String title);
}
