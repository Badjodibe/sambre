package com.sambre.services.offer;


import com.sambre.entities.offers.JobOffer;
import com.sambre.entities.offers.JobOfferRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    public JobOffer createJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public List<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }

    public Optional<JobOffer> getJobOfferById(Long id) {
        return jobOfferRepository.findById(id);
    }

    public JobOffer updateJobOffer(Long id, JobOffer updatedOffer) {
        return jobOfferRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedOffer.getTitle());
                    existing.setDescription(updatedOffer.getDescription());
                    existing.setLocation(updatedOffer.getLocation());
                    existing.setSalary(updatedOffer.getSalary());
                    existing.setSkills(updatedOffer.getSkills());
                    existing.setCompany(updatedOffer.getCompany());
                    return jobOfferRepository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("JobOffer not found"));
    }

    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }

    public List<JobOffer> findByTitleContainingIgnoreCase(String keyword) {
        return jobOfferRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<JobOffer> findByCompanyId(Long companyId) {
        return jobOfferRepository.findByCompanyId(companyId);
    }

    public List<JobOffer> findLatestJobOffers(int limit) {
        return jobOfferRepository.findTopNOrderByCreatedAtDesc(limit);
    }
}
