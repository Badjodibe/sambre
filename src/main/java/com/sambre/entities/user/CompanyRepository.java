package com.sambre.entities.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {

    Optional<Company> findByFiscalIdentificationNumber(String fiscalIdentificationNumber);
    Optional<Company> findByEmail(String email);
    List<Company> findBySector(String sector);

    @Query("SELECT c FROM Company c WHERE LOWER(c.sector) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Company> searchBySectorKeyword(String keyword);

    boolean existsByFiscalIdentificationNumber(String fiscalIdentificationNumber);
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"jobOffers"})
    Optional<Company> findWithJobOffersById(Long id);

    @EntityGraph(attributePaths = {"jobOffers"})
    List<Company> findAllWithJobOffers();

    Optional<Company> findByName(String name);

    List<Company> findByNameContainingIgnoreCase(String namePart);

    @Query("SELECT c FROM Company c WHERE SIZE(c.jobOffers) >= :minOffers")
    List<Company> findCompaniesWithMinJobOffers(int minOffers);

    @Query("SELECT c FROM Company c WHERE c.jobOffers IS EMPTY")
    List<Company> findCompaniesWithoutJobOffers();

    List<Company> findByLocationIgnoreCase(String location);

    long countBySector(String sector);

    boolean existsByName(String name);
}
