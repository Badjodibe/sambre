package com.sambre.services.security;


import com.sambre.config.messaging.email.EmailService;
import com.sambre.config.security.jwt.JwtService;
import com.sambre.dtos.security.AuthenticateRequest;
import com.sambre.dtos.security.AuthenticateResponse;
import com.sambre.dtos.security.TokenResponse;
import com.sambre.dtos.user.CandidateResponse;
import com.sambre.dtos.user.CandidateRequest;
import com.sambre.entities.enumerations.EmailTemplateName;
import com.sambre.entities.user.User;
import com.sambre.services.user.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final UserService userService;


    // Fonction pour générer les 6 numéros d'activations
    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i=0; i<length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();

    }

    // Enregistrer le token généré dans la base de données
    private String generateAndSaveActivationToken(
            CandidateRequest user) {
        // generate a token
        String generatedToken = generateActivationCode(6);
        TokenResponse token = TokenResponse.builder()
                .createAt(LocalDateTime.now())
                .expireAt(LocalDateTime.now().plusMinutes(15))
                .token(generatedToken)
                .usersId(user.getId())
                .build();

        tokenService.save(token);

        return generatedToken;
    }

    public void sendValidationEmail(CandidateRequest user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        String fullName = user.getPrenom() + " " + user.getNom();

        // send email
        emailService.sendEmail(
                user.getEmail(),
                fullName,
                "Validation de votre mail sur LivraiX",
                EmailTemplateName.ACTIVATE_ACCOUNT,
                newToken
        );
    }



    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());

        claims.put("fullName", user.fullName());
        claims.put("userId", user.getId());

        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticateResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        TokenResponse savedToken = tokenService.findByToken(token);
        Optional<CandidateResponse> usersDTO = userService.findById(savedToken.getUsersId());

        if (LocalDateTime.now().isAfter(savedToken.getExpireAt())){
            sendValidationEmail(userResponse.get());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email adress ");
        }

        // Activer accountBlocked pour la validation de mail du users
        userService.activeAccountBlocked(savedToken.getUsersId());

        // Mettre à jour la date de validité du token
        savedToken.setValidateAt(LocalDateTime.now());

        // Faire le update du token
        tokenService.updateToken(savedToken.getId(), savedToken);
    }
}
