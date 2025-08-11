package com.sambre.entities.utilis;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends CrudRepository<Skills, String> {

    // Trouver toutes les compétences par nom exact.
    List<Skills> findByName(String name);

    // Recherche insensible à la casse et partielle sur le nom.
    List<Skills> findByNameContainingIgnoreCase(String keyword);

    // Trouver toutes les compétences d’un certain niveau.
    List<Skills> findByLevel(String level);

    // Trouver toutes les compétences avec un nombre minimum d’années d’expérience.
    List<Skills> findByYearsGreaterThanEqual(int minYears);

    // Combiner critères : nom + minimum d’années.
    List<Skills> findByNameAndYearsGreaterThanEqual(String name, int minYears);

    // Retourner les compétences triées par années d’expérience (desc).
    @Query("SELECT s FROM Skills s ORDER BY s.years DESC")
    List<Skills> findAllOrderByYearsDesc();

    // Compter combien de personnes possèdent une compétence donnée.
    Long countByName(String name);
}
