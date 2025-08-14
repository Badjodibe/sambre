package com.sambre.sambre.web.offers;


import com.sambre.sambre.entities.enumerations.ApplicationStatus;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.services.offer.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/job-applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    /** 🔹 Récupérer toutes les candidatures */
    @GetMapping
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    /** 🔹 Récupérer une candidature par son ID */
    @GetMapping("/{id}")
    public Optional<JobApplication> getApplicationById(@PathVariable String id) {
        return jobApplicationService.getApplicationById(id);
    }

    /** 🔹 Récupérer toutes les candidatures d'une offre */
    @GetMapping("/offer/{jobOfferId}")
    public List<JobApplication> getApplicationsByJobOfferId(@PathVariable String jobOfferId) {
        return jobApplicationService.getApplicationsByJobOfferId(jobOfferId);
    }

    /** 🔹 Récupérer toutes les candidatures d'un candidat */
    @GetMapping("/candidate/{candidateId}")
    public List<JobApplication> getApplicationsByCandidateId(@PathVariable String candidateId) {
        return jobApplicationService.getApplicationsByCandidateId(candidateId);
    }

    /** 🔹 Récupérer toutes les candidatures par statut */
    @GetMapping("/status/{status}")
    public List<JobApplication> getApplicationsByStatus(@PathVariable ApplicationStatus status) {
        return jobApplicationService.getApplicationsByStatus(status);
    }

    /** 🔹 Vérifier si un candidat a déjà postulé à une offre */
    @GetMapping("/exists")
    public boolean hasCandidateApplied(
            @RequestParam String candidateId,
            @RequestParam String jobOfferId
    ) {
        return jobApplicationService.hasCandidateApplied(candidateId, jobOfferId);
    }

    /** 🔹 Compter le nombre de candidatures pour une offre */
    @GetMapping("/count/{jobOfferId}")
    public long countApplicationsForJobOffer(@PathVariable String jobOfferId) {
        return jobApplicationService.countApplicationsForJobOffer(jobOfferId);
    }

    /** 🔹 Récupérer les candidatures d'un candidat par statut */
    @GetMapping("/candidate/{candidateId}/status/{status}")
    public List<JobApplication> getCandidateApplicationsByStatus(
            @PathVariable String candidateId,
            @PathVariable ApplicationStatus status
    ) {
        return jobApplicationService.getCandidateApplicationsByStatus(candidateId, status);
    }

    /** 🔹 Récupérer les candidatures d'une offre par statut */
    @GetMapping("/offer/{jobOfferId}/status/{status}")
    public List<JobApplication> getApplicationsByJobOfferAndStatus(
            @PathVariable String jobOfferId,
            @PathVariable ApplicationStatus status
    ) {
        return jobApplicationService.getApplicationsByJobOfferAndStatus(jobOfferId, status);
    }

    /** 🔹 Créer ou mettre à jour une candidature */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobApplication createOrUpdateApplication(@RequestBody JobApplication application) {
        return jobApplicationService.save(application);
    }

    /** 🔹 Supprimer une candidature */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(@PathVariable String id) {
        jobApplicationService.deleteById(id);
    }
}

