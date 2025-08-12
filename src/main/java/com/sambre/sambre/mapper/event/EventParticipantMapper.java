package com.sambre.sambre.mapper.event;


import com.sambre.sambre.dtos.events.EventParticipantDTO;
import com.sambre.sambre.entities.events.EventParticipant;
import com.sambre.sambre.mapper.user.CandidateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = {  EventMapper.class } // utilise les mappers déjà existants
)
public interface EventParticipantMapper {

    EventParticipantMapper INSTANCE = Mappers.getMapper(EventParticipantMapper.class);

    // Entity -> DTO
    EventParticipantDTO toDTO(EventParticipant entity);

    // DTO -> Entity
    EventParticipant toEntity(EventParticipantDTO dto);
}
