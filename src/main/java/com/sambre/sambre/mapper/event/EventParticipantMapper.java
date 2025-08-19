package com.sambre.sambre.mapper.event;


import com.sambre.sambre.dtos.events.EventParticipantDTO;
import com.sambre.sambre.dtos.events.EventParticipantRequest;
import com.sambre.sambre.dtos.events.EventParticipantResponse;
import com.sambre.sambre.entities.events.EventParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface EventParticipantMapper {


    EventParticipantMapper INSTANCE = Mappers.getMapper(EventParticipantMapper.class);

    // 🔹 Request → Entity
    @Mapping(target = "eventParticipantId", ignore = true) // généré par JPA
    @Mapping(target = "event", ignore = true) // lié via eventId dans le service
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDateTime.now())")
    EventParticipant toEntity(EventParticipantRequest request);

    // 🔹 Entity → Response
    @Mapping(target = "eventParticipantId", source = "eventParticipantId")
    @Mapping(target = "eventId", expression = "java(eventParticipant.getEvent() != null ? eventParticipant.getEvent().getEventId() : null)")
    @Mapping(target = "eventName", expression = "java(eventParticipant.getEvent() != null ? eventParticipant.getEvent().getTitle() : null)")
    @Mapping(target = "registrationDate", expression = "java(eventParticipant.getRegistrationDate().toString())")
    EventParticipantResponse toResponse(EventParticipant eventParticipant);

}
