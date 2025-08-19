package com.sambre.sambre.mapper.security;

import com.sambre.sambre.config.security.jwt.Token;
import com.sambre.sambre.dtos.security.TokenResponse;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface TokenMapper {
    TokenResponse toDTO(Token user);
    Token toEntity(TokenResponse dto);
}
