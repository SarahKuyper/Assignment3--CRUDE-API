package com.Sarah.characters.controller;

import com.Sarah.characters.entity.OC;
import com.Sarah.characters.service.OCService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class OCController {

    private final OCService service;

    public OCController(OCService service) {
        this.service = service;
    }

    @GetMapping
    public List<OC> getAll(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) String name) {
        return service.getAll(species, name);
    }

    @GetMapping("/{id}")
    public OC getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OC create(@Valid @RequestBody OC c) {
        return service.create(c);
    }

    @PutMapping("/{id}")
    public OC update(@PathVariable long id, @Valid @RequestBody OC c) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}