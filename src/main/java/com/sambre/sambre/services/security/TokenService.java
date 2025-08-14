package com.sambre.sambre.services.security;


import com.sambre.sambre.config.security.jwt.Token;
import com.sambre.sambre.config.security.jwt.TokenRepository;
import com.sambre.sambre.dtos.security.TokenResponse;
import com.sambre.sambre.mapper.security.TokenMapper;
import com.sambre.sambre.services.ServiceInterfaceImpl;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService implements ServiceInterfaceImpl<TokenResponse> {
    private final TokenMapper tokenMapper;
    private final TokenRepository tokenRepository;

    @Override
    public Optional<TokenResponse> save(TokenResponse tokenDTO) {
        Token token = tokenMapper.toEntity(tokenDTO);

        var token1 = Token.builder()
                .validateAt(token.getValidateAt())
                .expireAt(token.getExpireAt())
                .createAt(token.getCreateAt())
                .usersId(token.getUsersId())
                .token(token.getToken())
                .build();

        return Optional.ofNullable(tokenMapper.toDTO(tokenRepository.save(token1)));
    }

    @Override
    public List<TokenResponse> getAll() {
        return null;
    }

    @Override
    public Optional<TokenResponse> findById(String id) {
        return Optional.empty();
    }

    public Optional<TokenResponse> findById(Long id) {
        Token token = tokenRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Token does not exist")
                );

        return Optional.ofNullable(tokenMapper.toDTO(token));
    }

    @Override
    public Optional<TokenResponse> update(String id, TokenResponse tokenDTO) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }

    public TokenResponse updateToken(String id, TokenResponse tokenDTO){
        var token = findById(id).isPresent()?findById(id).get():null;

        if (tokenDTO.getToken() != null){
            assert token != null;
            token.setToken(token.getToken());
            token.setValidateAt(tokenDTO.getValidateAt());
        }

        var token1 = tokenRepository.save(tokenMapper.toEntity(token));

        return tokenMapper.toDTO(token1);
    }


    public TokenResponse findByToken(String token){
        var token1 = tokenRepository.findByToken(token)
                .orElseThrow(
                        () -> new RuntimeException("Invalid token")
                );

        return tokenMapper.toDTO(token1);
    }



}