package com.sambre.sambre.entities.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {

    // Récupérer toutes les reviews d'une entreprise
    List<Review> findByCompanyId(String companyId);

    // Récupérer toutes les reviews d'un candidat
    List<Review> findByCandidateId(String candidateId);

    // Récupérer toutes les reviews d'une entreprise avec une note minimale
    List<Review> findByCompanyIdAndRatingGreaterThanEqual(String companyId, int rating);
}
