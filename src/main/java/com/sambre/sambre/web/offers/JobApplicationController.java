package com.sambre.sambre.web.offers;

import com.sambre.sambre.entities.enumerations.ApplicationStatus;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.services.offer.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    /** 🔹 Récupérer toutes les candidatures */
    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllApplications() {
        return ResponseEntity.ok(jobApplicationService.getAllApplications());
    }

    /** 🔹 Récupérer une candidature par son ID */
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable String id) {
        return jobApplicationService.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Récupérer toutes les candidatures d'une offre */
    @GetMapping("/offer/{jobOfferId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByJobOfferId(@PathVariable String jobOfferId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByJobOfferId(jobOfferId));
    }

    /** 🔹 Récupérer toutes les candidatures d'un candidat */
    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByCandidateId(@PathVariable String candidateId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByCandidateId(candidateId));
    }

    /** 🔹 Récupérer toutes les candidatures par statut */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplication>> getApplicationsByStatus(@PathVariable ApplicationStatus status) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByStatus(status));
    }

    /** 🔹 Vérifier si un candidat a déjà postulé à une offre */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> hasCandidateApplied(
            @RequestParam String candidateId,
            @RequestParam String jobOfferId
    ) {
        return ResponseEntity.ok(jobApplicationService.hasCandidateApplied(candidateId, jobOfferId));
    }

    /** 🔹 Compter le nombre de candidatures pour une offre */
    @GetMapping("/count/{jobOfferId}")
    public ResponseEntity<Long> countApplicationsForJobOffer(@PathVariable String jobOfferId) {
        return ResponseEntity.ok(jobApplicationService.countApplicationsForJobOffer(jobOfferId));
    }

    /** 🔹 Récupérer les candidatures d'un candidat par statut */
    @GetMapping("/candidate/{candidateId}/status/{status}")
    public ResponseEntity<List<JobApplication>> getCandidateApplicationsByStatus(
            @PathVariable String candidateId,
            @PathVariable ApplicationStatus status
    ) {
        return ResponseEntity.ok(jobApplicationService.getCandidateApplicationsByStatus(candidateId, status));
    }

    /** 🔹 Récupérer les candidatures d'une offre par statut */
    @GetMapping("/offer/{jobOfferId}/status/{status}")
    public ResponseEntity<List<JobApplication>> getApplicationsByJobOfferAndStatus(
            @PathVariable String jobOfferId,
            @PathVariable ApplicationStatus status
    ) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByJobOfferAndStatus(jobOfferId, status));
    }

    /** 🔹 Créer ou mettre à jour une candidature */
    @PostMapping
    public ResponseEntity<JobApplication> createOrUpdateApplication(@RequestBody JobApplication application) {
        JobApplication saved = jobApplicationService.save(application);
        return ResponseEntity.status(201).body(saved);
    }

    /** 🔹 Supprimer une candidature */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
        jobApplicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
