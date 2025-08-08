package com.sambre.entities.utilis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends CrudRepository<Social, String> {
}
