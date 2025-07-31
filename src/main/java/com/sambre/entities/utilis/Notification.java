package com.sambre.entities.utilis;

import com.sambre.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private boolean read = false;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;


}
