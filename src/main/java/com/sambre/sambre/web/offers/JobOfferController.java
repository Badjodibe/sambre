package com.sambre.sambre.web.offers;

import com.sambre.sambre.entities.offers.JobOffer;
import com.sambre.sambre.services.offer.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-offers")
@RequiredArgsConstructor
public class JobOfferController {

    private final JobOfferService jobOfferService;

    /** 🔹 Récupérer toutes les offres */
    @GetMapping
    public ResponseEntity<List<JobOffer>> getAll() {
        return ResponseEntity.ok(jobOfferService.getAll());
    }

    /** 🔹 Récupérer une offre par ID */
    @GetMapping("/{id}")
    public ResponseEntity<JobOffer> getById(@PathVariable String id) {
        return jobOfferService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Récupérer une offre avec la société */
    @GetMapping("/{id}/with-company")
    public ResponseEntity<JobOffer> getWithCompany(@PathVariable String id) {
        return jobOfferService.getWithCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Recherche par titre */
    @GetMapping("/search")
    public ResponseEntity<List<JobOffer>> searchByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(jobOfferService.searchByTitle(keyword));
    }

    /** 🔹 Recherche par société */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobOffer>> getByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(jobOfferService.getByCompanyId(companyId));
    }

    /** 🔹 Recherche par région */
    @GetMapping("/region/{region}")
    public ResponseEntity<List<JobOffer>> getByRegion(@PathVariable String region) {
        return ResponseEntity.ok(jobOfferService.getByRegion(region));
    }

    /** 🔹 Recherche par type de contrat */
    @GetMapping("/type/{contractType}")
    public ResponseEntity<List<JobOffer>> getByJobType(@PathVariable String contractType) {
        return ResponseEntity.ok(jobOfferService.getByJobType(contractType));
    }

    /** 🔹 Créer une offre */
    @PostMapping
    public ResponseEntity<JobOffer> create(@RequestBody JobOffer jobOffer) {
        return ResponseEntity.ok(jobOfferService.save(jobOffer));
    }

    /** 🔹 Mettre à jour une offre */
    @PutMapping("/{id}")
    public ResponseEntity<JobOffer> update(@PathVariable String id, @RequestBody JobOffer jobOffer) {
        if (jobOfferService.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        jobOffer.setJobOfferId(id);
        return ResponseEntity.ok(jobOfferService.save(jobOffer));
    }

    /** 🔹 Supprimer une offre */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (jobOfferService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

