package com.sambre.sambre.entities.user;

import com.sambre.sambre.entities.enumerations.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String userEmail);
    // Pour un rôle unique
    List<User> findByRoles(Role roles);

    // Pour plusieurs rôles
    List<User> findByRolesIn(Collection<Role> roles);

}
