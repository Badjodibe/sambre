package com.sambre.mapper.user;

import com.sambre.dtos.user.CandidateResponse;
import com.sambre.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    CandidateResponse toDTO(User user);
    User toEntity(CandidateResponse dto);
    List<CandidateResponse> toResponseList(List<User> users);
}
