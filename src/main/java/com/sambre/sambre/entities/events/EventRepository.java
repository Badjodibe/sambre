package com.sambre.sambre.entities.events;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, String> {

//    @EntityGraph(attributePaths = {"organizer"})
//    List<Event> findByOrganizerId(Long companyId);
}

