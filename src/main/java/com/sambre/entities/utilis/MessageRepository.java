package com.sambre.entities.utilis;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {
    @Query("SELECT m FROM Message m WHERE m.sender.id = :senderId")
    Iterable<Message> findBySender(@Param("senderId") String senderId);

    @Query("SELECT m FROM Message m WHERE m.receiver.id = :receiverId")
    Iterable<Message> findByReceiver(@Param("receiverId") String receiverId);

    @Query("SELECT m FROM Message m WHERE m.sender.id = :senderId AND m.receiver.id = :receiverId")
    Iterable<Message> findBySenderAndReceiver(@Param("senderId") String senderId, @Param("receiverId") String receiverId);
}
