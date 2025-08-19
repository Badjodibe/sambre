package com.sambre.sambre.mapper.user;

import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.dtos.user.UserDTO;
import com.sambre.sambre.dtos.user.UserRequest;
import com.sambre.sambre.dtos.user.UserResponse;
import com.sambre.sambre.dtos.utils.SocialRequest;
import com.sambre.sambre.dtos.utils.SocialResponse;
import com.sambre.sambre.entities.user.User;
import com.sambre.sambre.entities.utils.Social;
import com.sambre.sambre.mapper.utils.SocialMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

// Mapper pour User
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // 🔹 De UserRequest vers User (création)
    @Mapping(target = "userId", ignore = true) // géré par JPA
    @Mapping(target = "accountLocked", constant = "false")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "password", source = "password")
    User toEntity(UserRequest request);

    // 🔹 De User vers UserResponse (retour API)
    @Mapping(target = "fullName", expression = "java(user.getFirstname() + \" \" + user.getLastname())")
    UserResponse toResponse(User user);

    // 🔹 Mapping SocialRequest ↔ Social
    Social toEntity(SocialRequest request);
    SocialResponse toResponse(Social social);

    List<Social> toSocialEntities(List<SocialRequest> requests);
    List<SocialResponse> toSocialResponses(List<Social> socials);
}