package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.UserCredentinal;
import org.example.repository.UserCredentialRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialRepository userCredentialRepository;

    public String saveUser(UserCredentinal userCredentinal) {
        userCredentinal.setPassword(passwordEncoder.encode(userCredentinal.getPassword()));
        userCredentialRepository.save(userCredentinal);
        return "User " + userCredentinal.getUsername() + " was saved";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
