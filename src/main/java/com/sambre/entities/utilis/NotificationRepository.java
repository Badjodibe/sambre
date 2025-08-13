package com.sambre.entities.utilis;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

    // Récupérer toutes les notifications d’un utilisateur
    @Query("SELECT DISTINCT n FROM Notification n WHERE n.user.id = :userId")
    List<Notification> findByUser(@Param("userId") String userId);

    // Récupérer uniquement les notifications non lues d’un utilisateur
    @Query("SELECT DISTINCT n FROM Notification n WHERE n.user.id = :userId AND n.read = false")
    List<Notification> findByUserAndReadFalse(@Param("userId") String userId);

    // Récupérer les notifications d’un utilisateur créées après une date donnée
    List<Notification> findByUserAndCreatedAt(String userId, LocalDateTime date);

    // Supprimer toutes les notifications d’un utilisateur
    void deleteByUser(String userId);

    /**
     * ✅ Marquer toutes les notifications comme lues (en utilisant @Query pour mise à jour directe)
     * ⚠ Nécessite @Modifying et @Transactional dans le service
     */
    @Query("UPDATE Notification n SET n.read = true WHERE n.user.id = :userId")
    void markAllAsRead(String userId);
}
