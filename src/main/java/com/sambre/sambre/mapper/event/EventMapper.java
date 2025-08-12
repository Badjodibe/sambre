package com.sambre.sambre.mapper.event;


import com.sambre.sambre.dtos.events.EventDTO;
import com.sambre.sambre.entities.events.Event;
import com.sambre.sambre.mapper.user.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = { CompanyMapper.class } // On réutilise les autres mappers
)
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    /**
     * Mapping Entity -> DTO
     */
    @Mapping(target = "participants", ignore = true)
    EventDTO toDTO(Event event);

    /**
     * Mapping DTO -> Entity
     * ⚠️ Les participants et l’organizer doivent être gérés dans le service.
     */
    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    Event toEntity(EventDTO dto);
}

