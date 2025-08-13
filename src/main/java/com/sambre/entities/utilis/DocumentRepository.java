package com.sambre.entities.utilis;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, String> {
    // Trouver tous les documents d'un candidat donné
    @Query("SELECT DISTINCT d FROM Document d JOIN FETCH d.candidate c WHERE c.id = :id")
    List<Document> findByCandidate(@Param("id") Long candidateId);

    // Trouver tous les documents d'un certain type (ex: pdf)
    List<Document> findByType(String type);

    // Chercher un document par son nom exact
    Document findByName(String name);

    // Chercher tous les documents dont le nom contient une chaîne donnée (recherche partielle)
    @Query("SELECT DISTINCT d FROM Document d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Document> findByNameContainingIgnoreCase(@Param("keyword") String partialName);

}
