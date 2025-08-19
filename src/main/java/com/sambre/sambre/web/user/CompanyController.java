package com.sambre.sambre.web.user;



import com.sambre.sambre.dtos.user.CompanyDTO;
import com.sambre.sambre.dtos.user.CompanyRequest;
import com.sambre.sambre.dtos.user.CompanyResponse;
import com.sambre.sambre.entities.user.Company;
import com.sambre.sambre.mapper.user.CompanyMapper;
import com.sambre.sambre.services.user.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    /** 🔹 Récupérer toutes les entreprises */
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        List<CompanyResponse> responses = StreamSupport
                .stream(companyService.findAll().spliterator(), false)
                .map(companyMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    /** 🔹 Récupérer une entreprise par ID */
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable String id) {
        return companyService.findById(id)
                .map(companyMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Créer une entreprise */
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest) {
        Company entity = companyMapper.toEntity(companyRequest);
        Company saved = companyService.save(entity);
        CompanyResponse response = companyMapper.toResponse(saved);

        return ResponseEntity.created(URI.create("/api/v1/companies/" + response.id()))
                .body(response);
    }

    /** 🔹 Mettre à jour une entreprise */
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable String id,
                                                         @RequestBody CompanyRequest companyRequest) {
        Company updatedEntity = companyMapper.toEntity(companyRequest);
        Company updated = companyService.update(id, updatedEntity);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(companyMapper.toResponse(updated));
    }

    /** 🔹 Supprimer une entreprise */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        if (companyService.findById(id).isPresent()) {
            companyService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /** 🔹 Récupérer par secteur */
    @GetMapping("/sector/{sector}")
    public ResponseEntity<List<CompanyResponse>> getCompaniesBySector(@PathVariable String sector) {
        return ResponseEntity.ok(
                companyService.getBySector(sector).stream()
                        .map(companyMapper::toResponse)
                        .toList()
        );
    }

    /** 🔹 Récupérer par fiscal number */
    @GetMapping("/fiscal/{fiscalId}")
    public ResponseEntity<CompanyResponse> getCompanyByFiscalId(@PathVariable String fiscalId) {
        return companyService.getByFiscalNumber(fiscalId)
                .map(companyMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Récupérer toutes les entreprises avec leurs offres */
    @GetMapping("/with-job-offers")
    public ResponseEntity<List<CompanyResponse>> getAllWithJobOffers() {
        return ResponseEntity.ok(
                companyService.getAllWithJobOffers().stream()
                        .map(companyMapper::toResponse)
                        .toList()
        );
    }

    /** 🔹 Récupérer une entreprise par ID avec ses offres */
    @GetMapping("/{id}/with-job-offers")
    public ResponseEntity<CompanyResponse> getOneWithJobOffers(@PathVariable String id) {
        return companyService.getWithJobOffersById(id)
                .map(companyMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

