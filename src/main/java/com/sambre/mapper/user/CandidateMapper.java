package com.sambre.mapper.user;


import com.sambre.dtos.user.CandidateResponse;
import com.sambre.entities.user.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    // Convert DTO to Entity
    Candidate toEntity(com.sambre.dtos.user.CandidateResponse dto);

    // Convert Entity to Response
    CandidateResponse toCandidateResponse(Candidate candidate);

    // Convert List<Entity> to List<Response>
    List<CandidateResponse> toCandidateResponseList(List<Candidate> candidates);
}
