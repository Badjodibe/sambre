package com.sambre.sambre.web.user;


import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.dtos.user.CandidateRequest;
import com.sambre.sambre.dtos.user.CandidateResponse;
import com.sambre.sambre.entities.user.Candidate;
import com.sambre.sambre.entities.user.Company;
import com.sambre.sambre.mapper.user.CandidateMapper;
import com.sambre.sambre.services.user.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;

    /** 🔹 Récupérer tous les candidats */
    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAll();
        return ResponseEntity.ok(
                candidates.stream().map(candidateMapper::toResponse).toList()
        );
    }

    /** 🔹 Récupérer un candidat par ID */
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable String id) {
        return candidateService.getById(id)
                .map(candidateMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Récupérer un candidat par email */
    @GetMapping("/email/{email}")
    public ResponseEntity<CandidateResponse> getCandidateByEmail(@PathVariable String email) {
        return candidateService.getByEmail(email)
                .map(candidateMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Rechercher par nom ou prénom */
    @GetMapping("/search")
    public ResponseEntity<List<CandidateResponse>> searchCandidatesByName(@RequestParam("q") String name) {
        return ResponseEntity.ok(
                candidateService.searchByName(name).stream()
                        .map(candidateMapper::toResponse)
                        .toList()
        );
    }

    /** 🔹 Créer un candidat */
    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody CandidateRequest candidateRequest) {
        Candidate entity = candidateMapper.toEntity(candidateRequest);
        Candidate saved = candidateService.save(entity);
        CandidateResponse response = candidateMapper.toResponse(saved);

        return ResponseEntity.ok(response);

    }

    /** 🔹 Mettre à jour un candidat */
    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(@PathVariable String id,
                                                             @RequestBody CandidateRequest candidateRequest) {

        Candidate updatedEntity = candidateMapper.toEntity(candidateRequest);
        Candidate updated = candidateService.update(id, updatedEntity);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(candidateMapper.toResponse(updated));
    }

    /** 🔹 Supprimer un candidat */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable String id) {
        if (candidateService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

