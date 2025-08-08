package com.sambre.sambre.services.offer;

import com.sambre.sambre.entities.enumerations.ApplicationStatus;
import com.sambre.sambre.entities.offers.JobApplication;
import com.sambre.sambre.entities.offers.JobApplicationRepository;
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

    public List<JobApplication> getApplicationsByJobOfferId(String jobOfferId) {
        return jobApplicationRepository.findByJobOfferId(jobOfferId);
    }

    public List<JobApplication> getApplicationsByCandidateId(String candidateId) {
        return jobApplicationRepository.findByCandidateId(candidateId);
    }

    public List<JobApplication> getApplicationsByStatus(ApplicationStatus status) {
        return jobApplicationRepository.findByStatus(status);
    }

    public boolean hasCandidateApplied(String candidateId, String jobOfferId) {
        return jobApplicationRepository.existsByCandidateIdAndJobOfferId(candidateId, jobOfferId);
    }

    public long countApplicationsForJobOffer(String jobOfferId) {
        return jobApplicationRepository.countByJobOfferId(jobOfferId);
    }

    public List<JobApplication> getCandidateApplicationsByStatus(String candidateId, ApplicationStatus status) {
        return jobApplicationRepository.findByCandidateIdAndStatus(candidateId, status);
    }

//    public List<JobApplication> searchApplicationsByCoverLetter(String keyword) {
//        return jobApplicationRepository.findByCoverLetterContainingIgnoreCase(keyword);
//    }

    public List<JobApplication> getApplicationsByJobOfferAndStatus(String jobOfferId, ApplicationStatus status) {
        return jobApplicationRepository.findByJobOfferIdAndStatus(jobOfferId, status);
    }

    public JobApplication save(JobApplication application) {
        return jobApplicationRepository.save(application);
    }

    public void deleteById(String id) {
        jobApplicationRepository.deleteById(id);
    }
}
