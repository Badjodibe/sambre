package com.sambre.sambre.services.user;

import com.sambre.sambre.entities.user.Company;
import com.sambre.sambre.entities.user.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company register(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> findById(String id) {
        return companyRepository.findById(id);
    }


    public Optional<Company> getByFiscalNumber(String fiscalNumber) {
        return companyRepository.findByFiscalIdentificationNumber(fiscalNumber);
    }

    public Optional<Company> getByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    public Optional<Company> getByName(String name) {
        return companyRepository.findByName(name);
    }

    public List<Company> getBySector(String sector) {
        return companyRepository.findBySector(sector);
    }

    public List<Company> searchBySector(String keyword) {
        return companyRepository.searchBySectorKeyword(keyword);
    }

    public List<Company> searchByName(String namePart) {
        return companyRepository.findByNameContainingIgnoreCase(namePart);
    }

    public List<Company> getAllWithJobOffers() {
        return companyRepository.findAllWithJobOffers();
    }

    public Optional<Company> getWithJobOffersById(String id) {
        return companyRepository.findWithJobOffersByUserId(id);
    }

    public List<Company> getCompaniesWithMinOffers(int minOffers) {
        return companyRepository.findCompaniesWithMinJobOffers(minOffers);
    }

    public List<Company> getCompaniesWithoutOffers() {
        return companyRepository.findCompaniesWithoutJobOffers();
    }

    public List<Company> getByLocation(String location) {
        return companyRepository.findByLocationIgnoreCase(location);
    }

    public long countBySector(String sector) {
        return companyRepository.countBySector(sector);
    }

    public boolean existsByFiscalNumber(String fiscalNumber) {
        return companyRepository.existsByFiscalIdentificationNumber(fiscalNumber);
    }

    public boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    public boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    public Company save(Company company) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        company.setDateCreation(currentDateTime);
        return companyRepository.save(company);
    }

    public void deleteById(String id) {
        companyRepository.deleteById(id);
    }

    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company update(String id, Company company){

        return companyRepository.save(company);
    }
}
