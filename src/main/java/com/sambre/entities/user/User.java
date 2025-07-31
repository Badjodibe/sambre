package com.sambre.entities.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.sambre.entities.enumerations.Role;
import com.sambre.entities.utilis.Social;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //@NotBlank(message = "Le nom ne peut pas être vide ou null")
    private String lastname;

    //@NotBlank(message = "Le prénom ne peut pas être vide ou null")
    private String firstname;

    @Column(unique = true)
    //@NotBlank(message = "L'email ne peut pas être vide ou null")
    private String email;

    //@NotBlank(message = "Le mot de passe ne peut pas être vide ou null")
    private String password;

    private String state;

    private String profile;

    private String tel;

    private List<Social> socials;

    //@NotBlank(message = "Le role ne peut pas être vide ou null")
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean accountLocked;
    private boolean enabled;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;


    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime last_modified;



    @Override
    public String getName() {
        return email;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the single role string to a list with one element
        return Stream.of(this.role)
                .map(r -> (GrantedAuthority) r::toString)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String fullName(){
        return firstname + " " + lastname;
    }
}