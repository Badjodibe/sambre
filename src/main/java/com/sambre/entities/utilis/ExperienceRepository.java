package com.sambre.entities.utilis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, String> {

}
