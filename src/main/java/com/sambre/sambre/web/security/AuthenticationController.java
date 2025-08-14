package com.sambre.sambre.web.security;


import com.sambre.sambre.dtos.security.AuthenticateRequest;
import com.sambre.sambre.dtos.security.AuthenticateResponse;
import com.sambre.sambre.services.security.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.sambre.sambre.dtos.user.CandidateDTO;
import com.sambre.sambre.dtos.user.CompanyDTO;

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
    @PostMapping("/register-candidate")
    public ResponseEntity<CandidateDTO> registerCandidate(@RequestBody @Valid CandidateDTO candidateRequest) throws MessagingException {
        CandidateDTO savedCandidate = authenticationService.registerCandidate(candidateRequest);
        return ResponseEntity.ok(savedCandidate);
    }

    /** 🔹 Inscription Entreprise */
    @PostMapping("/register-company")
    public ResponseEntity<CompanyDTO> registerCompany(@RequestBody @Valid CompanyDTO companyRequest) throws MessagingException {
        CompanyDTO savedCompany = authenticationService.registerCompany(companyRequest);
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
    @GetMapping("/validate-email/{token}")
    public ResponseEntity<Void> activateAccount(@PathVariable String token) throws MessagingException {
        authenticationService.activateAccount(token);
        return ResponseEntity.accepted().build();
    }
}
