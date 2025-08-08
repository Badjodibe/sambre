package com.sambre.sambre.config.security.jwt;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime createAt;
    private LocalDateTime expireAt;
    private LocalDateTime validateAt;
    private String token;
    private String usersId;

    //@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    //@OnDelete(action = OnDeleteAction.NO_ACTION)
    //private Users users;
}
