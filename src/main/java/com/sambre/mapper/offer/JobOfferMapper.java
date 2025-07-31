package com.sambre.mapper.offer;


import com.sambre.dtos.offers.JobOfferRequest;
import com.sambre.dtos.offers.JobOfferResponse;
import com.sambre.entities.offers.JobOffer;
import com.sambre.mapper.user.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface JobOfferMapper {

    JobOfferMapper INSTANCE = Mappers.getMapper(JobOfferMapper.class);

    JobOfferResponse  toJobResponse(JobOffer jobOffer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true) // à définir dans le service à partir du contexte utilisateur
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applications", ignore = true)
    JobOffer toEntity(JobOfferRequest request);
}
