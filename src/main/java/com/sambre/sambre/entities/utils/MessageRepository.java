package com.sambre.sambre.entities.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {

    // 🔹 Récupérer tous les messages envoyés par un utilisateur
    List<Message> findBySenderUserId(String senderId);

    // 🔹 Récupérer tous les messages reçus par un utilisateur
    List<Message> findByReceiverUserId(String receiverId);

    // 🔹 Récupérer l'historique des messages entre deux utilisateurs (dans les deux sens)
    List<Message> findBySenderUserIdAndReceiverUserIdOrSenderUserIdAndReceiverUserIdOrderByTimestampAsc(
            String senderId1, String receiverId1,
            String senderId2, String receiverId2
    );

    // 🔹 Récupérer les messages contenant un certain mot clé (dans le contenu)
    List<Message> findByContentContainingIgnoreCase(String keyword);
}
