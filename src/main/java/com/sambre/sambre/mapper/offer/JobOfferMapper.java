package com.sambre.sambre.mapper.offer;


import com.sambre.sambre.dtos.offers.JobOfferDTO;
import com.sambre.sambre.entities.offers.JobOffer;
import com.sambre.sambre.entities.user.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {

    JobOfferMapper INSTANCE = Mappers.getMapper(JobOfferMapper.class);

    /**
     * Mapping de DTO -> Entity
     * On crée un objet Company uniquement avec l'ID pour éviter de charger toute l'entité.
     */
    @Mapping(target = "company", expression = "java(mapCompanyId(dto.companyId()))")
    @Mapping(target = "jobApplications", ignore = true) // On ignore pour éviter la boucle infinie
    JobOffer toEntity(JobOfferDTO dto);

    /**
     * Mapping de Entity -> DTO
     * On prend uniquement l'ID de la company.
     */
    @Mapping(target = "companyId", source = "company.id")
    JobOfferDTO toDTO(JobOffer entity);

    /**
     * Méthode utilitaire pour mapper l'ID de la company en objet Company.
     */
    default Company mapCompanyId(String companyId) {
        if (companyId == null) return null;
        Company company = new Company();
        company.setId(companyId);
        return company;
    }
}
