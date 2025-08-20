package com.sambre.sambre.services.security;

import com.sambre.sambre.config.messaging.email.EmailService;
import com.sambre.sambre.config.messaging.sms.SmsService;
import com.sambre.sambre.config.security.jwt.JwtService;
import com.sambre.sambre.dtos.security.AuthenticateRequest;
import com.sambre.sambre.dtos.security.AuthenticateResponse;
import com.sambre.sambre.dtos.security.TokenResponse;
import com.sambre.sambre.dtos.user.*;
import com.sambre.sambre.entities.enumerations.EmailTemplateName;
import com.sambre.sambre.entities.user.User;
import com.sambre.sambre.mapper.user.CandidateMapper;
import com.sambre.sambre.mapper.user.CompanyMapper;
import com.sambre.sambre.services.user.CandidateService;
import com.sambre.sambre.services.user.CompanyService;
import com.sambre.sambre.services.user.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final UserService userService;
    private final CandidateService candidateService;
    private final CompanyService companyService;

    private final CandidateMapper candidateMapper;
    private final CompanyMapper companyMapper;
    private final SmsService smsVasService;



    /** 🔹 Génération code à 6 chiffres */
    private String generateActivationCode() {
        String characters = "0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(index));
        }
        return codeBuilder.toString();
    }



    /** 🔹 Génération + sauvegarde token */
    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode();
        TokenResponse token = TokenResponse.builder()
                .createAt(LocalDateTime.now())
                .expireAt(LocalDateTime.now().plusMinutes(15))
                .token(generatedToken)
                .usersId(user.getUserId()) // ID en String
                .build();
        tokenService.save(token);
        return generatedToken;
    }

    /** send activated sms for activation account **/

    public String sendMessage(String phoneNumber, User user) {
        String newToken = generateAndSaveActivationToken(user);
        String fullName = user.getFirstname() + " " + user.getLastname();
        String message = smsVasService.sendSms(phoneNumber, fullName + " Votre code d'activation de compte est : " + newToken);
        return newToken;
    }

    /** 🔹 Envoi mail validation */
    public void sendValidationEmail(User user) throws MessagingException {
        String newToken = generateAndSaveActivationToken(user);
        String fullName = user.getFirstname() + " " + user.getLastname();

        emailService.sendEmail(
                user.getEmail(),
                fullName,
                "Validation de votre compte sur Sambre",
                EmailTemplateName.ACTIVATE_ACCOUNT,
                newToken
        );
    }

    /** 🔹 Authentification */
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
        claims.put("userId", user.getUserId());
        claims.put("roles", user.getRoles()
                .stream()
                .map(Enum::name)
                .toList()
        );

        String jwt = jwtService.generateToken(claims, user);

        return AuthenticateResponse.builder()
                .token(jwt)
                .build();
    }

    /** 🔹 Activation compte */
    public void activateAccount(String token) throws MessagingException {
        TokenResponse savedToken = tokenService.findByToken(token);

        User user = userService.findById(savedToken.getUsersId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (LocalDateTime.now().isAfter(savedToken.getExpireAt())) {
            sendValidationEmail(user);
            throw new RuntimeException("Token expiré. Un nouveau token a été envoyé par email.");
        }

        userService.activeAccountBlocked(user.getUserId());

        savedToken.setValidateAt(LocalDateTime.now());
        tokenService.updateToken(savedToken.getUsersId(), savedToken);
    }

    /** 🔹 Enregistrement candidat */
    public CandidateResponse registerCandidate(CandidateRequest candidateRequest) throws MessagingException {
        var candidate_ = candidateMapper.toEntity(candidateRequest);
        var candidate = candidateService.register(candidate_);
        String phone = candidateRequest.tel();
        String message = sendMessage(phone, candidate);
        return candidateMapper.toResponse(candidate);
    }

    /** 🔹 Enregistrement entreprise */
    public CompanyResponse registerCompany(CompanyRequest companyRequest) throws MessagingException {
        var company_ = companyMapper.toEntity(companyRequest);
        var company = companyService.register(company_);
        String phone = companyRequest.tel();
        String message = sendMessage(phone, company_);
        return companyMapper.toResponse(company);
    }

//    /** 🔹 Enregistrement admin (candidat avec rôle admin) */
//    public CandidateDTO registerAdmin(CandidateDTO candidateRequest) throws MessagingException {
//        var admin = candidateService.register(candidateRequest);
//        sendValidationEmail(admin);
//        return candidateMapper.toDTO(admin);
//    }


}
