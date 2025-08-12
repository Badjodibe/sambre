package com.sambre.sambre.services.user;


import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.entities.user.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    /*
   private final CandidateRepository candidateRepository;
   private final CandidateMapper candidateMapper;

   public List<CandidateResponse> findAllWithExperiences() {
       return candidateRepository.findAllWithExperiences()
               .stream()
               .map(candidateMapper::toCandidateResponse)
               .collect(Collectors.toList());
   }

   public Optional<CandidateResponse> findByIdWithExperiences(Long id) {
       return candidateRepository.findByIdWithExperiences(id)
               .map(candidateMapper::toCandidateResponse);
   }

    public Optional<CandidateResponse> findByEmail(String email) {
        return candidateRepository.findByEmail(email)
                .map(candidateMapper::toCandidateResponse);
    }

    public List<CandidateResponse> findBySocialNetwork(String socialName) {
        return candidateRepository.findBySocialNetwork(socialName)
                .stream()
                .map(candidateMapper::toCandidateResponse)
                .collect(Collectors.toList());
    }

    public List<CandidateResponse> findByExperienceTitleKeyword(String keyword) {
        return candidateRepository.findByExperiencesTitleKeyword(keyword)
                .stream()
                .map(candidateMapper::toCandidateResponse)
                .collect(Collectors.toList());
    }

    public CandidateResponse save(CandidateResponse candidateDTO) {
        Candidate entity = candidateMapper.toEntity(candidateDTO);
        return candidateMapper.toCandidateResponse(candidateRepository.save(entity));
    }

    public Optional<CandidateResponse> update(String id, CandidateResponse candidateDTO) {
        return candidateRepository.findById(id).map(existing -> {
            Candidate updated = candidateMapper.toEntity(candidateDTO);
            updated.setId(id);
            return candidateMapper.toCandidateResponse(candidateRepository.save(updated));
        });
    }

    public void delete(String id) {
        candidateRepository.deleteById(id);
    }



    */
}

