package com.sambre.sambre.web.user;


import com.sambre.sambre.dtos.user.CandidateDTO;
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

    /** 🔹 Récupérer tous les candidats */
    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {

         //return ResponseEntity.ok(candidateService.getAll());
            return ResponseEntity.status(501).build(); // TODO
    }

    /** 🔹 Récupérer un candidat par ID */
    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable String id) {
        return candidateService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Récupérer un candidat par email */
    @GetMapping("/email/{email}")
    public ResponseEntity<CandidateDTO> getCandidateByEmail(@PathVariable String email) {
        return candidateService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** 🔹 Rechercher par nom ou prénom */
    @GetMapping("/search")
    public ResponseEntity<List<CandidateDTO>> searchCandidatesByName(@RequestParam("q") String name) {
        return ResponseEntity.ok(candidateService.searchByName(name));
    }

    /** 🔹 Créer un candidat */
    @PostMapping
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        CandidateDTO created = candidateService.save(candidateDTO);
        return ResponseEntity.created(URI.create("/api/v1/candidates/" + created.id()))
                .body(created);
    }

    /** 🔹 Mettre à jour un candidat */
    @PutMapping("/{id}")
    public ResponseEntity<CandidateDTO> updateCandidate(@PathVariable String id,
                                                        @RequestBody CandidateDTO candidateDTO) {
        return candidateService.update(id, candidateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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

