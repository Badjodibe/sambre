package com.sambre.sambre.entities.utils;

import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.entities.utils.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, String> {

    // 🔹 Récupérer tous les documents d’un candidat par son userId
    List<Document> findByCandidateUserId(String candidateId);

    // 🔹 Récupérer tous les documents d’un candidat (entité Candidate)
    List<Document> findByCandidate(Candidate candidate);

    // 🔹 Trouver par nom exact
    List<Document> findByName(String name);

    // 🔹 Chercher par type (pdf, docx, etc.)
    List<Document> findByTypeIgnoreCase(String type);

    // 🔹 Chercher par nom contenant un mot-clé (insensible à la casse)
    List<Document> findByNameContainingIgnoreCase(String keyword);

    // 🔹 Supprimer tous les documents d’un candidat
    void deleteByCandidateUserId(String candidateId);
}

