package com.sambre.sambre.mapper.event;


import com.sambre.sambre.dtos.events.EventParticipantDTO;
import com.sambre.sambre.entities.events.EventParticipant;
import com.sambre.sambre.mapper.user.CandidateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = {  EventMapper.class } // utilise les mappers déjà existants
)
public interface EventParticipantMapper {

    EventParticipantMapper INSTANCE = Mappers.getMapper(EventParticipantMapper.class);

    // Entity -> DTO
    @Mapping(target = "participant", ignore = true)
    @Mapping(target = "event", ignore = true)
    EventParticipantDTO toDTO(EventParticipant entity);

    // DTO -> Entity
    @Mapping(target = "participant", ignore = true)
    @Mapping(target = "event", ignore = true)
    EventParticipant toEntity(EventParticipantDTO dto);
}
