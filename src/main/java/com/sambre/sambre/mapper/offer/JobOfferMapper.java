package com.sambre.sambre.mapper.offer;


import com.sambre.sambre.dtos.offers.JobOfferRequest;
import com.sambre.sambre.dtos.offers.JobOfferResponse;
import com.sambre.sambre.entities.offers.JobOffer;
import com.sambre.sambre.mapper.user.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface JobOfferMapper {
    JobOfferMapper INSTANCE = Mappers.getMapper(JobOfferMapper.class);
    JobOfferResponse  toJobResponse(JobOffer jobOffer);

    JobOffer toEntity(JobOfferRequest request);
}
