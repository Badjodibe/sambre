package com.sambre.sambre.mapper.user;

import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.dtos.user.UserDTO;
import com.sambre.sambre.entities.user.User;
import com.sambre.sambre.mapper.utils.SocialMapper;
import org.mapstruct.*;

import java.util.List;

// Mapper pour User
@Mapper(
        componentModel = "spring",
        uses = { SocialMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    CandidateDTO toDTO(User user);

    User toEntity(CandidateDTO dto);

    List<CandidateDTO> toResponseList(List<User> users);

    // Entity -> DTO
    @Mappings({
            @Mapping(target = "roles", expression = "java( user.getRoles()==null ? null : user.getRoles().stream().map(Enum::name).toList() )"),
            @Mapping(target = "fullName", ignore = true),     // on le calcule après
            @Mapping(target = "socials", source = "socials"),
            @Mapping(target = "profile", source = "profile"),
            @Mapping(target = "email", source = "email")
            // password n’existe pas dans le DTO => jamais exposé
    })
    UserDTO toDto(User user);

    // DTO -> Entity (attention aux listes)
    @Mappings({
            @Mapping(target = "roles", expression = "java( dto.roles()==null ? null : dto.roles().stream().map(Role::valueOf).toList() )"),
            @Mapping(target = "password", ignore = true),     // on ne set pas le password via DTO
            @Mapping(target = "accountLocked", ignore = true),
            @Mapping(target = "enabled", ignore = true),
            @Mapping(target = "dateCreation", ignore = true),
            @Mapping(target = "last_modified", ignore = true)
    })
    User toEntity(UserDTO dto);

    // Update in-place (PATCH/PUT) sur une entité existante
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "roles", expression = "java( dto.roles()==null ? target.getRoles() : dto.roles().stream().map(Role::valueOf).toList() )"),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "dateCreation", ignore = true),
            @Mapping(target = "last_modified", ignore = true)
    })
    void updateEntityFromDto(UserDTO dto, @MappingTarget User target);

    // Post-traitement pour fullName
    /*
    @AfterMapping
    default void afterToDto(User user, @MappingTarget UserDTO.UserDTOBuilder builder) {
        String fn = (user.getFirstname()==null ? "" : user.getFirstname()) +
                " " +
                (user.getLastname()==null ? "" : user.getLastname());
        builder.fullName(fn.trim());
    }

     */
}