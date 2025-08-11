package com.sambre.entities.utilis;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

    // Récupérer toutes les notifications d’un utilisateur
    List<Notification> findByUserId(String userId);

    // Récupérer uniquement les notifications non lues d’un utilisateur
    List<Notification> findByUserIdAndReadFalse(String userId);

    // Récupérer les notifications d’un utilisateur créées après une date donnée
    List<Notification> findByUserIdAndCreatedAtAfter(String userId, LocalDateTime date);

    // Supprimer toutes les notifications d’un utilisateur
    void deleteByUserId(String userId);

    /**
     * ✅ Marquer toutes les notifications comme lues (en utilisant @Query pour mise à jour directe)
     * ⚠ Nécessite @Modifying et @Transactional dans le service
     */
    @Query("UPDATE Notification n SET n.read = true WHERE n.user.id = :userId")
    void markAllAsRead(String userId);
}
