package com.sambre.entities.utilis;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialRepository extends CrudRepository<Social, String> {

    // Trouver tous les liens sociaux par nom exact.
    List<Social> findByName(String name);

    // Recherche insensible à la casse sur le nom du réseau social.
    List<Social> findByNameContainingIgnoreCase(String keyword);

    // Trouver un réseau social par URL exacte.
    Social findByUrl(String url);

    // Compter combien d’entrées correspondent à un nom donné.
    Long countByName(String name);

    // Retourner tous les réseaux sociaux triés par nom (ordre alphabétique).
    @Query("SELECT s FROM Social s ORDER BY s.name ASC")
    List<Social> findAllOrderByNameAsc();

    // Retourner tous les réseaux sociaux qui commencent par une certaine lettre (insensible à la casse).
    List<Social> findByNameStartingWithIgnoreCase(String letter);
}
