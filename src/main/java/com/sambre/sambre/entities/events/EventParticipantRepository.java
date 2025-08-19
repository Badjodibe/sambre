package com.sambre.sambre.entities.events;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, String> {

    // 🔹 Trouver tous les participants d’un événement
    List<EventParticipant> findByEventEventId(String eventId);

    // 🔹 Trouver tous les événements auxquels un candidat est inscrit
    List<EventParticipant> findByParticipantUserId(String candidateId);

    // 🔹 Vérifier si un candidat est déjà inscrit à un événement
    boolean existsByParticipantUserIdAndEventEventId(String candidateId, String eventId);

    // 🔹 Compter les participants d’un événement
    long countByEventEventId(String eventId);

    // 🔹 Supprimer tous les participants d’un événement
    void deleteByEventEventId(String eventId);

    // 🔹 Supprimer toutes les inscriptions d’un candidat
    void deleteByParticipantUserId(String candidateId);
}
