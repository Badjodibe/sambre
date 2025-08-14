package com.sambre.sambre.entities.events;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, String> {

    // 🔹 Trouver tous les participants d’un événement
    List<EventParticipant> findByEventId(String eventId);

    // 🔹 Trouver tous les événements auxquels un candidat est inscrit
    List<EventParticipant> findByParticipantId(String candidateId);

    // 🔹 Vérifier si un candidat est déjà inscrit à un événement
    boolean existsByParticipantIdAndEventId(String candidateId, String eventId);

    // 🔹 Compter les participants d’un événement
    long countByEventId(String eventId);

    // 🔹 Supprimer tous les participants d’un événement
    void deleteByEventId(String eventId);

    // 🔹 Supprimer toutes les inscriptions d’un candidat
    void deleteByParticipantId(String candidateId);
}
