package com.ab4system.extremesport.repository;

import com.ab4system.extremesport.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String name);
}
