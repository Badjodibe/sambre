package com.sambre.entities.events;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventParticipantRepository extends CrudRepository<EventParticipant, Long> {

    @EntityGraph(attributePaths = {"participant", "event"})
    List<EventParticipant> findByParticipantId(Long candidateId);

    boolean existsByParticipantIdAndEventId(Long candidateId, Long eventId);
}
