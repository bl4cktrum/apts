package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.models.requests.LoginRequest;
import dev.bl4cktrum.apts.api.models.requests.RegisterRequest;
import dev.bl4cktrum.apts.api.models.responses.AuthenticationResponse;
import dev.bl4cktrum.apts.api.repositories.RelevantRepository;
import dev.bl4cktrum.apts.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RelevantRepository relevantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var relevant = Relevant.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        relevantRepository.save(relevant);
        var jwtToken = jwtService.generateToken(relevant);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        var relevant = relevantRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(relevant);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
