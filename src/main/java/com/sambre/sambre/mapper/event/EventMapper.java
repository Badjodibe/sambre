package com.sambre.sambre.mapper.event;


import com.sambre.sambre.dtos.events.EventDTO;
import com.sambre.sambre.dtos.events.EventRequest;
import com.sambre.sambre.dtos.events.EventResponse;
import com.sambre.sambre.entities.events.Event;
import com.sambre.sambre.mapper.user.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    // 🔹 Request → Entity
    @Mapping(target = "eventId", ignore = true) // généré par JPA
    @Mapping(target = "organizer", ignore = true) // défini côté service avec company connectée
    @Mapping(target = "participants", ignore = true) // ajoutés après inscription
    Event toEntity(EventRequest request);

    // 🔹 Entity → Response
    @Mapping(target = "eventId", source = "eventId")
//    @Mapping(target = "organizerId", expression = "java(event.getOrganizer() != null ? event.getOrganizer().getId() : null)")
//    @Mapping(target = "organizerName", expression = "java(event.getOrganizer() != null ? event.getOrganizer().getName() : null)")
    @Mapping(target = "participantCount", expression = "java(event.getParticipants() != null ? event.getParticipants().size() : 0)")
    EventResponse toResponse(Event event);
}

