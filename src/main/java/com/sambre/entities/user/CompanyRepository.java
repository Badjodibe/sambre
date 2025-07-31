package com.sambre.entities.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    // Trouver une entreprise par son numéro d'identification fiscale
    Optional<Company> findByFiscalIdentificationNumber(String fiscalIdentificationNumber);

    // Trouver une entreprise par email (hérité de User)
    Optional<Company> findByEmail(String email);

    // Trouver les entreprises par secteur d'activité
    List<Company> findBySector(String sector);

    // Rechercher par secteur avec mot-clé partiel (case insensitive)
    @Query("SELECT c FROM Company c WHERE LOWER(c.sector) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Company> searchBySectorKeyword(String keyword);

    // Vérifier l'existence d'une entreprise par son numéro fiscal
    boolean existsByFiscalIdentificationNumber(String fiscalIdentificationNumber);

    // Vérifier l'existence d'une entreprise par email
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"jobOffers"})
    Optional<Company> findWithJobOffersById(Long id);

    @EntityGraph(attributePaths = {"jobOffers"})
    List<Company> findAllWithJobOffers();
}
