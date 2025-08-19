package com.sambre.sambre.entities.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

    // Récupérer toutes les notifications d'un utilisateur
    List<Notification> findByUserUserId(String userId);

    // Récupérer toutes les notifications non lues d'un utilisateur
    List<Notification> findByUserUserIdAndReadFalse(String userId);

    // Supprimer toutes les notifications d'un utilisateur
    void deleteByUserUserId(String userId);
}
