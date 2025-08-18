package com.sambre.sambre.mapper.user;


import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.mapper.event.EventParticipantMapper;
import com.sambre.sambre.mapper.offer.JobApplicationMapper;
import com.sambre.sambre.mapper.utils.ExperienceMapper;
import com.sambre.sambre.mapper.utils.SocialMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = {
                SocialMapper.class,              // pour mapper List<SocialDTO>
                ExperienceMapper.class,          // pour mapper List<ExperienceDTO>
                JobApplicationMapper.class,      // pour mapper List<JobApplicationDTO>
                EventParticipantMapper.class,    // pour mapper List<EventParticipantDTO>
                UserMapper.class
        }
)
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    // Entity -> DTO
    @Mapping(target = "experiences", source = "experiences")
    @Mapping(target = "jobApplications", source = "jobApplications")
    @Mapping(target = "registeredEvents", source = "registeredEvents")
    @Mapping(target = "fullName", ignore = true)
    CandidateDTO toDTO(Candidate entity);

    // DTO -> Entity
    @Mapping(target = "experiences", source = "experiences")
    @Mapping(target = "jobApplications", source = "jobApplications")
    @Mapping(target = "registeredEvents", source = "registeredEvents")
    Candidate toEntity(CandidateDTO dto);
}

