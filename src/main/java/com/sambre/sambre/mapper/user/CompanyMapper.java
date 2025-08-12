package com.sambre.sambre.mapper.user;


import com.sambre.sambre.dtos.user.CompanyDTO;
import com.sambre.sambre.entities.user.Company;
import com.sambre.sambre.mapper.offer.JobOfferMapper;
import com.sambre.sambre.mapper.event.EventMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = { JobOfferMapper.class } // Pour mapper les relations
)
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    // Entity -> DTO
    CompanyDTO toDTO(Company company);

    // DTO -> Entity
    @Mapping(target = "jobOffers", ignore = true) // À gérer dans le service
    Company toEntity(CompanyDTO dto);
}
