package com.ab4system.extremesport.repository;

import com.ab4system.extremesport.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByName(String name);
}
