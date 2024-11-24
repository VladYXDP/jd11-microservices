package org.example.persistence;

import org.example.persistence.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findByPhoneCode(Integer phoneCode);
}
