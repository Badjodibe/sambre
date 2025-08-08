package com.sambre.sambre.entities.events;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventParticipantRepository extends CrudRepository<EventParticipant, String> {

    @EntityGraph(attributePaths = {"participant", "event"})
    List<EventParticipant> findByParticipantId(String candidateId);

    boolean existsByParticipantIdAndEventId(String candidateId, String eventId);
}
