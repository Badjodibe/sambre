package com.sambre.sambre.mapper.security;

import com.sambre.sambre.config.security.jwt.Token;
import com.sambre.sambre.dtos.security.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface TokenMapper {
    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);
    TokenResponse toDTO(Token user);
    Token toEntity(TokenResponse dto);
}
