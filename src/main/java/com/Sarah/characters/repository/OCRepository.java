package com.Sarah.characters.repository;

import com.Sarah.characters.entity.OC;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OCRepository extends JpaRepository<OC, Long> {

    List<OC> findBySpeciesIgnoreCase(String species);

    List<OC> findByNameContainingIgnoreCase(String name);
}