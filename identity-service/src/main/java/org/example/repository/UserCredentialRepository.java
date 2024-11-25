package org.example.repository;

import org.example.entity.UserCredentinal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredentinal, Long> {

    UserCredentinal findByUsername(String username);
}
