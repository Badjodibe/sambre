package com.sambre.sambre.web.user;



import com.sambre.sambre.dtos.user.CompanyDTO;
import com.sambre.sambre.entities.user.Company;
import com.sambre.sambre.mapper.user.CompanyMapper;
import com.sambre.sambre.services.user.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    @GetMapping
    public ResponseEntity<Iterable<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
        return companyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyRequest) {
        var company = companyMapper.toEntity(companyRequest);
        Company created = companyService.save(company);
        return ResponseEntity.ok(companyMapper.toDTO(created));
    }



   /*
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyDTO) {
        return companyService.update(id, companyDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    public ResponseEntity<List<Company>> getCompaniesBySector(@PathVariable String sector) {
        return ResponseEntity.ok(companyService.getBySector(sector));
    }

    /*
    @GetMapping("/search")
    public ResponseEntity<List<CompanyResponse>> searchBySector(@RequestParam String keyword) {
        return ResponseEntity.ok(companyService.searchBySectorKeyword(keyword));
    }

     */

    @GetMapping("/fiscal/{fiscalId}")
    public ResponseEntity<Company> getCompanyByFiscalId(@PathVariable String fiscalId) {
        return companyService.getByFiscalNumber(fiscalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-job-offers")
    public ResponseEntity<List<Company>> getAllWithJobOffers() {
        return ResponseEntity.ok(companyService.getAllWithJobOffers());
    }

    @GetMapping("/{id}/with-job-offers")
    public ResponseEntity<Company> getOneWithJobOffers(@PathVariable String id) {
        return companyService.getWithJobOffersById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

