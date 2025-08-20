package com.sambre.sambre.services.user;


import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.entities.user.CandidateRepository;
import com.sambre.sambre.util.exception.DuplicateResourceException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    /** 🔹 Récupérer un candidat par ID */
    public Optional<Candidate> getById(String id) {
        return candidateRepository.findById(id);
    }

    /** 🔹 Créer un candidat */
    public Candidate register(Candidate candidate) {
        if (candidateRepository.findByEmail(candidate.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Un utilisateur avec cet email existe déjà : " + candidate.getEmail());
        }
        candidate.setDateCreation(LocalDateTime.now());
        return candidateRepository.save(candidate);
    }

    /** 🔹 Récupérer un candidat par email */
    public Optional<Candidate> getByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    /**
     * 🔹 Rechercher des candidats par nom
     */
    public Optional<Candidate> searchByName(String name) {
        return candidateRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name);
    }

    /** 🔹 Récupérer tous les candidats */
    public List<Candidate> getAll() {
        return (List<Candidate>)candidateRepository.findAll();
    }

    /** 🔹 Sauvegarder un candidat */
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    /** 🔹 Mettre à jour un candidat */
    public Candidate update(String id, Candidate updatedCandidate) {
        Candidate existing = candidateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidat introuvable avec id : " + id));

        updatedCandidate.setUserId(existing.getUserId());  // conserver l’ID
        updatedCandidate.setDateCreation(existing.getDateCreation()); // garder la date de création
        return candidateRepository.save(updatedCandidate);
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
