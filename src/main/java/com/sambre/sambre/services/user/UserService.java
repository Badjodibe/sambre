package com.sambre.sambre.services.user;


import com.sambre.sambre.entities.user.User;
import com.sambre.sambre.entities.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Trouver un utilisateur par son ID
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Trouver un utilisateur par son email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Sauvegarder un utilisateur
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Supprimer un utilisateur
     */
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    /**
     * Lister tous les utilisateurs
     */
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Charger un utilisateur pour Spring Security (si besoin)
     */
    public User loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + username));
    }

    /*
    public Optional<User> activeAccount(Long id, User usersDTO){
        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()){
            User users = byId.get();
            users.setEnabled(true);

            User save = userRepository.save(users);
            return Optional.ofNullable(UserMapper.INSTANCE.toEntity(user));
        }
        else {
            return Optional.empty();
        }
    }

     */

    public User activeAccountBlocked(String id) {
        User byId =  findById(id).isPresent()?findById(id).get():null;

        if (byId == null){
            throw new EntityNotFoundException("Personne avec l'ID "+ id + " n'existe pas");
        }
        byId.setAccountLocked(true);
        userRepository.save(byId);

        return byId;
    }


}

