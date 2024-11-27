package org.example.config;

import org.example.entity.UserCredential;
import org.example.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = repository.findByUsername(username);
        if (userCredential != null) {
            return new CustomUserDetails(userCredential);
        } else {
            throw new RuntimeException("user not found with username " + username);
        }
    }
}
