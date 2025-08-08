package com.sambre.sambre.mapper.user;


import com.sambre.sambre.dtos.user.CandidateResponse;
import com.sambre.sambre.entities.user.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    // Convert DTO to Entity
    Candidate toEntity(CandidateResponse dto);

    // Convert Entity to Response
    CandidateResponse toCandidateResponse(Candidate candidate);

    // Convert List<Entity> to List<Response>
    List<CandidateResponse> toCandidateResponseList(List<Candidate> candidates);
}
