package com.sambre.sambre.mapper.user;


import com.sambre.sambre.dtos.user.CompanyDTO;
import com.sambre.sambre.dtos.user.CompanyRequest;
import com.sambre.sambre.dtos.user.CompanyResponse;
import com.sambre.sambre.entities.user.Company;
import com.sambre.sambre.mapper.event.EventMapper;
import com.sambre.sambre.mapper.offer.JobOfferMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = { UserMapper.class, JobOfferMapper.class, EventMapper.class }
)
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "jobOffers", ignore = true) // créées après
    @Mapping(target = "events", ignore = true)    // créés après
    Company toEntity(CompanyRequest request);

    // 🔹 Entity → Response
    @Mapping(target = "fullName", expression = "java(company.getFirstname() + \" \" + company.getLastname())")
    CompanyResponse toResponse(Company company);
}
