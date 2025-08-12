package com.sambre.sambre.mapper.user;


import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.mapper.event.EventParticipantMapper;
import com.sambre.sambre.mapper.offer.JobApplicationMapper;
import com.sambre.sambre.mapper.utils.ExperienceMapper;
import com.sambre.sambre.mapper.utils.SocialMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = {
                SocialMapper.class,              // pour mapper List<SocialDTO>
                ExperienceMapper.class,          // pour mapper List<ExperienceDTO>
                JobApplicationMapper.class,      // pour mapper List<JobApplicationDTO>
                EventParticipantMapper.class     // pour mapper List<EventParticipantDTO>
        }
)
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    // Entity -> DTO
    CandidateDTO toDTO(Candidate entity);

    // DTO -> Entity
    Candidate toEntity(CandidateDTO dto);
}

