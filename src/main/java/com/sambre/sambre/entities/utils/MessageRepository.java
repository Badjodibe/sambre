package com.sambre.sambre.entities.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {
    Iterable<Message> findBySenderId(String senderId);
    Iterable<Message> findByReceiverId(String receiverId);
    Iterable<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
