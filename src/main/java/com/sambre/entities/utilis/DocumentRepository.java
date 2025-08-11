package com.sambre.entities.utilis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, String> {
    // Trouver tous les documents d'un candidat donné
    List<Document> findByCandidateId(Long candidateId);

    // Trouver tous les documents d'un certain type (ex: pdf)
    List<Document> findByType(String type);

    // Chercher un document par son nom exact
    Document findByName(String name);

    // Chercher tous les documents dont le nom contient une chaîne donnée (recherche partielle)
    List<Document> findByNameContainingIgnoreCase(String partialName);

}
