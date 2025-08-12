package com.sambre.sambre.services.security;

import com.sambre.sambre.config.messaging.email.EmailService;
import com.sambre.sambre.config.security.jwt.JwtService;
import com.sambre.sambre.dtos.security.AuthenticateRequest;
import com.sambre.sambre.dtos.security.AuthenticateResponse;
import com.sambre.sambre.dtos.security.TokenResponse;
import com.sambre.sambre.entities.user.User;
import com.sambre.sambre.services.user.CandidateService;
import com.sambre.sambre.services.user.CompanyService;
import com.sambre.sambre.services.user.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final TokenService tokenService;
    @Autowired
    private final EmailService emailService;
    private final UserService userService;
    private final CandidateService candidateService;
    private final CompanyService companyService;

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(index));
        }
        return codeBuilder.toString();
    }

    private String generateAndSaveActivationToken(User user) {
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

//    public void sendValidationEmail(User user) throws MessagingException {
//        String newToken = generateAndSaveActivationToken(user);
//
//        String fullName = user.getFirstname() + " " + user.getLastname();
//
//        emailService.sendEmail(
//                user.getEmail(),
//                fullName,
//                "Validation de votre compte sur Sambre",
//                EmailTemplateName.ACTIVATE_ACCOUNT,
//                newToken
//        );
//    }

    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        var claims = new HashMap<String, Object>();
        claims.put("fullName", user.fullName());
        claims.put("userId", user.getId());
// Pour une liste d'Enum Role
        claims.put("roles", user.getRoles()
                .stream()
                .map(Enum::name)
                .toList()
        );// ROLE_RECRUITER ou ROLE_CANDIDATE par exemple

        String jwt = jwtService.generateToken(claims, user);

        return AuthenticateResponse.builder()
                .token(jwt)
                .build();
    }

    /*public void activateAccount(String token) throws MessagingException {
        TokenResponse savedToken = tokenService.findByToken(token);
        Optional<User> userOptional = userService.findById(Long.valueOf(savedToken.getUsersId()));

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();

        if (LocalDateTime.now().isAfter(savedToken.getExpireAt())) {
            sendValidationEmail(user);
            throw new RuntimeException("Token expiré. Un nouveau token a été envoyé par email.");
        }

        userService.activeAccountBlocked(user.getId());

        savedToken.setValidateAt(LocalDateTime.now());
        tokenService.updateToken(savedToken.getId(), savedToken);
    }


    public Optional<CandidateResponse> registerCandidate(CandidateRequest candidateRequest) throws MessagingException {
        return null;
    }

    public Optional<CompanyResponse> registerCompany(CompanyRequest companyRequest) throws MessagingException {
   
        return null;

    }

     */
}
