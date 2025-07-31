package com.sambre.mapper.security;

import com.sambre.config.security.jwt.Token;
import com.sambre.dtos.security.TokenResponse;
import org.mapstruct.factory.Mappers;

public interface TokenMapper {
    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);
    TokenResponse toDTO(Token user);
    Token toEntity(TokenResponse dto);
}
