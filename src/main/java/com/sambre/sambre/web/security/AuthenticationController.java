package com.sambre.sambre.web.security;


import com.sambre.sambre.dtos.security.AuthenticateRequest;
import com.sambre.sambre.dtos.security.AuthenticateResponse;
import com.sambre.sambre.dtos.user.*;
import com.sambre.sambre.services.security.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /** 🔹 Test route */
    @GetMapping({"", "/"})
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("ok");
    }

    /** 🔹 Inscription Candidat */
    @PostMapping("/registerCandidate")
    public ResponseEntity<CandidateResponse> registerCandidate(@RequestBody @Valid CandidateRequest candidateRequest) throws MessagingException {
        CandidateResponse savedCandidate = authenticationService.registerCandidate(candidateRequest);
        return ResponseEntity.ok(savedCandidate);
    }

    /** 🔹 Inscription Entreprise */
    @PostMapping("/registerCompany")
    public ResponseEntity<CompanyResponse> registerCompany(@RequestBody @Valid CompanyRequest companyRequest) throws MessagingException {
        CompanyResponse savedCompany = authenticationService.registerCompany(companyRequest);
        return ResponseEntity.ok(savedCompany);
    }

    /** 🔹 Inscription Admin (reprend logique candidat) */
//    @PostMapping("/register-admin")
//    public ResponseEntity<CandidateDTO> registerAdmin(@RequestBody @Valid CandidateDTO candidateRequest) throws MessagingException {
//        CandidateDTO savedAdmin = authenticationService.registerAdmin(candidateRequest);
//        return ResponseEntity.ok(savedAdmin);
//    }

    /** 🔹 Connexion */
    @PostMapping("/login")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody @Valid AuthenticateRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /** 🔹 Activation de compte via lien mail */
    @GetMapping("/validateAccount/{token}")
    public ResponseEntity<Void> activateAccount(@PathVariable String token) throws MessagingException {
        authenticationService.activateAccount(token);
        return ResponseEntity.accepted().build();
    }
}
