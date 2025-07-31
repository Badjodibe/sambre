package com.sambre.mapper.user;


import com.sambre.dtos.user.CompanyResponse;
import com.sambre.entities.user.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, JobOfferMapper.class, EventMapper.class})
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "jobOffers", source = "jobOffers")
    @Mapping(target = "events", source = "events")
    CompanyResponse toCompanyResponse(Company company);
}
