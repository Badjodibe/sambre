package com.sambre.sambre.entities.events;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {

    // 🔹 Trouver tous les événements organisés par une entreprise donnée (via userId)
    List<Event> findByOrganizerUserId(String organizerId);

    // 🔹 Chercher par titre (contient, insensible à la casse)
    List<Event> findByTitleContainingIgnoreCase(String keyword);

    // 🔹 Événements dans une plage de dates
    List<Event> findByStartDateBetween(LocalDateTime start, LocalDateTime end);

    // 🔹 Événements à venir
    @Query("SELECT e FROM Event e WHERE e.startDate > CURRENT_TIMESTAMP ORDER BY e.startDate ASC")
    List<Event> findUpcomingEvents();

    // 🔹 Événements passés
    @Query("SELECT e FROM Event e WHERE e.endDate < CURRENT_TIMESTAMP ORDER BY e.endDate DESC")
    List<Event> findPastEvents();

    // 🔹 Compter les événements par entreprise (organizer.userId)
    long countByOrganizerUserId(String organizerId);
}


