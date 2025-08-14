package com.sambre.sambre.entities.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, String> {
    // Trouver un candidat par son email (hérité de User)
    Optional<Candidate> findByEmail(String email);
    Optional<Candidate> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname);
    /*
    // Charger un candidat avec ses expériences (fetch join pour éviter N+1)
    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.experiences WHERE c.id = :id")
    Optional<Candidate> findByIdWithExperiences(Long id);

    // Trouver tous les candidats avec au moins une expérience
    @Query("SELECT DISTINCT c FROM Candidate c JOIN FETCH c.experiences")
    List<Candidate> findAllWithExperiences();
    */


    // Trouver les candidats avec un réseau social donné
    @Query("SELECT DISTINCT c FROM Candidate c JOIN c.socials s WHERE s.name = :socialName")
    List<Candidate> findBySocialNetwork(String socialName);

    // Trouver les candidats par mot-clé dans le titre d'expérience
    @Query("SELECT DISTINCT c FROM Candidate c JOIN c.experiences e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Candidate> findByExperiencesTitleKeyword(String keyword);


}
