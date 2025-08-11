package com.sambre.sambre.entities.utils;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {

    // Trouver toutes les évaluations d’une entreprise
    List<Review> findByCompanyId(String companyId);

    // Trouver toutes les évaluations d’un candidat.
    List<Review> findByCandidateId(String candidateId);

    /**
     * Récupérer les avis d’une entreprise avec une note spécifique.
     * @param companyId ID de l’entreprise.
     * @param rating note (1 à 5).
     * @return liste des avis filtrés.
     */
    List<Review> findByCompanyIdAndRating(String companyId, int rating);

    // Trouver les avis d’une entreprise créés après une date donnée.
    List<Review> findByCompanyIdAndCreatedAtAfter(String companyId, LocalDateTime date);

    /**
     * 📊 Calculer la moyenne des notes d’une entreprise.
     * ⚠ Utilise JPQL pour faire le calcul directement en base.
     * @param companyId ID de l’entreprise.
     * @return moyenne des notes ou null si pas d’avis.
     */
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.company.id = :companyId")
    Optional<Double> getAverageRatingByCompanyId(String companyId);

    /**
     * 🔢 Compter le nombre d’avis pour une entreprise.
     * @param companyId ID de l’entreprise.
     * @return nombre total d’avis.
     */
    Long countByCompanyId(String companyId);
}
