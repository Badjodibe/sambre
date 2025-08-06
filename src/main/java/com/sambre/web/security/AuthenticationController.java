package com.sambre.web.security;


import com.sambre.dtos.security.AuthenticateRequest;
import com.sambre.dtos.security.AuthenticateResponse;
import com.sambre.dtos.user.CandidateRequest;
import com.sambre.dtos.user.CandidateResponse;
import com.sambre.dtos.user.CompanyRequest;
import com.sambre.services.security.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registerCandidate")
    public Optional<CandidateResponse> registerCandidate(@RequestBody CandidateRequest candidateRequest) throws MessagingException {
        return authenticationService.registerCandidate(candidateRequest);
    }

    @PostMapping("/registerCompany")
    public void registerCompany(@RequestBody CompanyRequest companyRequest) throws MessagingException {
        authenticationService.registerCompany(companyRequest);
    }

    @PostMapping("/registerAdmin")
    public Optional<CandidateResponse> registerAdmin(@RequestBody CandidateRequest candidateRequest) throws MessagingException {
        return authenticationService.registerCandidate(candidateRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateResponse> authenticate(
            @RequestBody @Valid AuthenticateRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

//    @GetMapping("/valider-mail/{token}")
//    public ResponseEntity<?> activateAccountBlock(@PathVariable String token) throws MessagingException {
//        authenticationService.activateAccount(token);
//        return ResponseEntity.accepted().build();
//    }



}
