package org.example.persistence;

import org.example.persistence.entity.PhoneCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneCodeRepository extends JpaRepository<PhoneCode, Integer> {
}
