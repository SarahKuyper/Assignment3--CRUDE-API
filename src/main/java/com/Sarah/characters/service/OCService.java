package com.Sarah.characters.service;

import com.Sarah.characters.entity.OC;
import com.Sarah.characters.repository.OCRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OCService {

    private final OCRepository repository;

    public OCService(OCRepository repository) {
        this.repository = repository;
    }

    public List<OC> getAll(String species, String name) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name);
        }
        if (species != null && !species.isBlank()) {
            return repository.findBySpeciesIgnoreCase(species);
        }
        return repository.findAll();
    }

    public OC getById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Character not found: " + id));
    }

    public OC create(OC c) {
        return repository.save(c);
    }

    public OC update(long id, OC updated) {
        OC existing = getById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setSpecies(updated.getSpecies());
        existing.setOccupancy(updated.getOccupancy());
        existing.setAbilities(updated.getAbilities());
        existing.setInterests(updated.getInterests());
        existing.setActiveDate(updated.getActiveDate());
        return repository.save(existing);
    }

    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Character not found: " + id);
        }
        repository.deleteById(id);
    }
}