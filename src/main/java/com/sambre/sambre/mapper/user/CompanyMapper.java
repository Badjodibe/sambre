package com.sambre.sambre.mapper.user;


import com.sambre.sambre.dtos.user.CompanyResponse;
import com.sambre.sambre.entities.user.Company;
import com.sambre.sambre.mapper.offer.JobOfferMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, JobOfferMapper.class})
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "jobOffers", source = "jobOffers")
    CompanyResponse toCompanyResponse(Company company);
}
