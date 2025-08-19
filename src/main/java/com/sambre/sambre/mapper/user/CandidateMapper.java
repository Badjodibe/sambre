package com.sambre.sambre.mapper.user;


import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.dtos.user.CandidateRequest;
import com.sambre.sambre.dtos.user.CandidateResponse;
import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.mapper.event.EventParticipantMapper;
import com.sambre.sambre.mapper.offer.JobApplicationMapper;
import com.sambre.sambre.mapper.utils.ExperienceMapper;
import com.sambre.sambre.mapper.utils.SocialMapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring",
        uses = { UserMapper.class, ExperienceMapper.class, JobApplicationMapper.class, EventParticipantMapper.class }
)
public interface CandidateMapper {

        CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);


        // 🔹 De UserRequest vers User (création)
        @Mapping(target = "accountLocked", constant = "false")
        @Mapping(target = "enabled", constant = "true")
        @Mapping(target = "password", source = "password")
        Candidate toEntity(CandidateRequest request);

        // 🔹 Entity → Response
        @Mapping(target = "fullName", expression = "java(candidate.getFirstname() + \" \" + candidate.getLastname())")
        @Mapping(target = "id", source = "userId")
        CandidateResponse toResponse(Candidate candidate);
}
