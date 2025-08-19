package com.sambre.sambre.mapper.offer;


import com.sambre.sambre.dtos.offers.JobApplicationDTO;
import com.sambre.sambre.dtos.offers.JobApplicationRequest;
import com.sambre.sambre.dtos.offers.JobApplicationResponse;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.entities.offers.JobOffer;
import com.sambre.sambre.entities.user.Candidate;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface JobApplicationMapper {

    JobApplicationMapper INSTANCE = Mappers.getMapper(JobApplicationMapper.class);

    // 🔹 Request → Entity
    @Mapping(target = "jobApplicationId", ignore = true) // généré par JPA
    @Mapping(target = "candidate", ignore = true) // récupéré via user connecté
    @Mapping(target = "jobOffer", ignore = true)  // lié en service avec jobOfferId
    @Mapping(target = "applicationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(com.sambre.sambre.entities.enumerations.ApplicationStatus.PENDING)")
    JobApplication toEntity(JobApplicationRequest request);

    // 🔹 Entity → Response
    @Mapping(target = "id", source = "jobApplicationId")
    @Mapping(target = "applicationDate", expression = "java(jobApplication.getApplicationDate().toString())")
    //@Mapping(target = "candidateId", expression = "java(jobApplication.getCandidate() != null ? jobApplication.getCandidate().getId() : null)")
    @Mapping(target = "candidateName", expression = "java(jobApplication.getCandidate() != null ? jobApplication.getCandidate().getFirstname() + \" \" + jobApplication.getCandidate().getLastname() : null)")
    @Mapping(target = "jobOfferId", expression = "java(jobApplication.getJobOffer() != null ? jobApplication.getJobOffer().getJobOfferId() : null)")
    @Mapping(target = "jobOfferTitle", expression = "java(jobApplication.getJobOffer() != null ? jobApplication.getJobOffer().getTitle() : null)")
    JobApplicationResponse toResponse(JobApplication jobApplication);
}

