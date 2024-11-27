package org.example.repository;

import org.example.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

    UserCredential findByUsername(String username);
}
