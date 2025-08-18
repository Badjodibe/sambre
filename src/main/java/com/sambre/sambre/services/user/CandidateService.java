package com.sambre.sambre.services.user;


import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.entities.user.CandidateRepository;
import com.sambre.sambre.mapper.user.CandidateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }

    /** 🔹 Récupérer un candidat par ID */
    public Optional<CandidateDTO> getById(String id) {
        return candidateRepository.findById(id)
                .map(candidateMapper::toDTO);
    }

    /** 🔹 Créer un candidate */
    public Candidate register(Candidate candidate) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        candidate.setDateCreation(currentDateTime);
        return candidateRepository.save(candidate);
    }

    /** 🔹 Récupérer un candidat par email */
    public Optional<CandidateDTO> getByEmail(String email) {
        return candidateRepository.findByEmail(email)
                .map(candidateMapper::toDTO);
    }

    /** 🔹 Rechercher des candidats par nom */
    public List<CandidateDTO> searchByName(String name) {
        return candidateRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name)
                .stream()
                .map(candidateMapper::toDTO)
                .collect(Collectors.toList());
    }

//    /** 🔹 Récupérer tous les candidats */
//
//    public List<CandidateDTO> getAll() {
//        return candidateRepository.findAll()
//                .stream()
//                .map(candidateMapper::toDTO)
//                .toList();
//    }

    /** 🔹 Sauvegarder un candidat */
    public CandidateDTO save(CandidateDTO candidateDTO) {
        Candidate saved = candidateRepository.save(candidateMapper.toEntity(candidateDTO));
        return candidateMapper.toDTO(saved);
    }

    /** 🔹 Mettre à jour un candidat */
    public Optional<CandidateDTO> update(String id, CandidateDTO candidateDTO) {
        return candidateRepository.findById(id)
                .map(existing -> {
                    Candidate updated = candidateMapper.toEntity(candidateDTO);
                    updated.setId(existing.getId()); // Conserver l'ID
                    return candidateMapper.toDTO(candidateRepository.save(updated));
                });
    }

    /** 🔹 Supprimer un candidat */
    public boolean deleteById(String id) {
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
