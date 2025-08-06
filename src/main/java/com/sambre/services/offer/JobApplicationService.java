package com.sambre.services.offer;

import com.sambre.entities.offers.JobApplication;
import com.sambre.entities.offers.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    @Autowired
    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public Optional<JobApplication> getApplicationById(String id) {
        return jobApplicationRepository.findById(id);
    }

    public List<JobApplication> getApplicationsByJobOfferId(Long jobOfferId) {
        return jobApplicationRepository.findByJobOfferId(jobOfferId);
    }

    public List<JobApplication> getApplicationsByCandidateId(Long candidateId) {
        return jobApplicationRepository.findByCandidateId(candidateId);
    }

    public List<JobApplication> getApplicationsByStatus(String status) {
        return jobApplicationRepository.findByStatusIgnoreCase(status);
    }

    public boolean hasCandidateApplied(Long candidateId, Long jobOfferId) {
        return jobApplicationRepository.existsByCandidateIdAndJobOfferId(candidateId, jobOfferId);
    }

    public long countApplicationsForJobOffer(Long jobOfferId) {
        return jobApplicationRepository.countByJobOfferId(jobOfferId);
    }

    public List<JobApplication> getCandidateApplicationsByStatus(Long candidateId, String status) {
        return jobApplicationRepository.findByCandidateIdAndStatusIgnoreCase(candidateId, status);
    }

    public List<JobApplication> searchApplicationsByCoverLetter(String keyword) {
        return jobApplicationRepository.findByCoverLetterContainingIgnoreCase(keyword);
    }

    public List<JobApplication> getApplicationsByJobOfferAndStatus(Long jobOfferId, String status) {
        return jobApplicationRepository.findByJobOfferIdAndStatusIgnoreCase(jobOfferId, status);
    }

    public JobApplication save(JobApplication application) {
        return jobApplicationRepository.save(application);
    }

    public void deleteById(String id) {
        jobApplicationRepository.deleteById(id);
    }
}
