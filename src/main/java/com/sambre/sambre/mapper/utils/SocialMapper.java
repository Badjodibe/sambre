package com.sambre.sambre.mapper.utils;

import com.sambre.sambre.dtos.utils.SocialDTO;
import com.sambre.sambre.entities.utils.Social;
import org.mapstruct.*;

// Mapper pour Social
@Mapper(componentModel = "spring")
public interface SocialMapper {
    SocialDTO toDto(Social entity);
    Social toEntity(SocialDTO dto);
}