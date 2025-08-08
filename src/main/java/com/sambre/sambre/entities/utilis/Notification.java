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
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String message;
    private boolean read = false;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;


}
