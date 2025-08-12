package com.sambre.sambre.mapper.offer;


import com.sambre.sambre.dtos.offers.JobApplicationDTO;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.entities.offers.JobOffer;
import com.sambre.sambre.entities.user.Candidate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface JobApplicationMapper {

    /* =======================
     *   Entity -> DTO
     * ======================= */
    @Mappings({
            @Mapping(target = "candidateId", source = "candidate.id"),
            @Mapping(target = "jobOfferId", source = "jobOffer.id")
    })
    JobApplicationDTO toDto(JobApplication entity);

    /* =======================
     *   DTO -> Entity (création)
     * ======================= */
    @Mappings({
            // On reconstitue des références "id-only"
            @Mapping(target = "candidate", source = "candidateId", qualifiedByName = "candidateFromId"),
            @Mapping(target = "jobOffer", source = "jobOfferId", qualifiedByName = "jobOfferFromId"),
            // Laisser l'applicationDate par défaut (gérée par l'entité)
            @Mapping(target = "applicationDate", ignore = true),
            // Laisser le status par défaut (PENDING) si tu veux
            @Mapping(target = "status", ignore = true),
            // id ignoré côté création (généré par JPA)
            @Mapping(target = "id", ignore = true)
    })
    JobApplication toEntity(JobApplicationDTO dto);

    /* =======================
     *   DTO -> Entity (update partiel)
     * ======================= */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "candidate", source = "candidateId", qualifiedByName = "candidateFromId"),
            @Mapping(target = "jobOffer", source = "jobOfferId", qualifiedByName = "jobOfferFromId"),
            // Tu peux choisir d'autoriser ou non la mise à jour du status
            // @Mapping(target = "status", ignore = true)
            // applicationDate non touchée
            @Mapping(target = "applicationDate", ignore = true)
    })
    void updateFromDto(JobApplicationDTO dto, @MappingTarget JobApplication entity);

    /* =======================
     *   Helpers pour relations
     * ======================= */

    @Named("candidateFromId")
    default Candidate candidateFromId(String id) {
        if (id == null) return null;
        Candidate c = new Candidate();
        c.setId(id);
        return c;
    }

    @Named("jobOfferFromId")
    default JobOffer jobOfferFromId(String id) {
        if (id == null) return null;
        JobOffer j = new JobOffer();
        j.setId(id);
        return j;
    }
}

