package com.sambre.entities.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String userEmail);
    public List<User> findByRole(String role); // si tu stockes les rôles sous forme de champ string

}
