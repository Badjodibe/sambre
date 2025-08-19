package com.sambre.sambre.mapper.offer;


import com.sambre.sambre.dtos.offers.*;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.entities.offers.JobOffer;
import com.sambre.sambre.entities.user.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {

    JobOfferMapper INSTANCE = Mappers.getMapper(JobOfferMapper.class);

    // 🔹 Request → Entity
    @Mapping(target = "jobOfferId", ignore = true) // généré par JPA
    @Mapping(target = "company", ignore = true) // défini dans le service via user connecté
    @Mapping(target = "jobApplications", ignore = true) // créées après par les candidats
    //@Mapping(target = "offerStatus", expression = "java(com.sambre.sambre.entities.enumerations.JobOfferStatus.OPEN)")
    JobOffer toEntity(JobOfferRequest request);

    // 🔹 Entity → Response
    @Mapping(target = "jobOfferId", source = "jobOfferId")
    @Mapping(target = "companyId", expression = "java(jobOffer.getCompany() != null ? jobOffer.getCompany().getUserId() : null)")
    @Mapping(target = "companyName", expression = "java(jobOffer.getCompany() != null ? jobOffer.getCompany().getName() : null)")
    JobOfferResponse toResponse(JobOffer jobOffer);
}
