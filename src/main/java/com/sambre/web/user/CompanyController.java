package com.sambre.web.user;



import com.sambre.entities.user.Company;
import com.sambre.services.user.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

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
/*
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyResponse companyRequest) {
        CompanyResponse created = companyService.save(companyRequest);
        return ResponseEntity.ok(created);
    }

 */

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
    public ResponseEntity<Company> getOneWithJobOffers(@PathVariable Long id) {
        return companyService.getWithJobOffersById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

