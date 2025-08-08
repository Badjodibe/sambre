package com.sambre.sambre.entities.utilis;

import com.sambre.sambre.entities.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private String content;
    private LocalDateTime timestamp;
}
