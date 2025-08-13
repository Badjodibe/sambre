package com.sambre.sambre.entities.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends CrudRepository<Skills, String> {

    // Trouver toutes les skills par nom (ignore case)
    List<Skills> findByNameIgnoreCase(String name);

    // Trouver toutes les skills par niveau
    List<Skills> findByLevelIgnoreCase(String level);

    // Trouver toutes les skills avec un minimum d'années d'expérience
    List<Skills> findByYearsGreaterThanEqual(int years);
}
