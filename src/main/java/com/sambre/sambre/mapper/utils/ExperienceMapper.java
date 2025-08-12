package com.sambre.sambre.mapper.utils;


import com.sambre.sambre.dtos.utils.ExperienceDTO;
import com.sambre.sambre.entities.utils.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    // Entity -> DTO
    ExperienceDTO toDTO(Experience entity);

    // DTO -> Entity
    Experience toEntity(ExperienceDTO dto);
}
