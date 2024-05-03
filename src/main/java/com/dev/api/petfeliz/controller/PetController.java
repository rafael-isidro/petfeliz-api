package com.dev.api.petfeliz.controller;

import com.dev.api.petfeliz.entity.PetEntity;
import com.dev.api.petfeliz.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PetEntity> findAllPets() {
        return petService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userId}")
    public List<PetEntity> findAllPetsByUserId(@PathVariable Long userId) {
        return petService.findAllPetsByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PetEntity getPetById(@PathVariable Long id) {
        return petService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet with ID " + id + " not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PetEntity createPet(@RequestBody PetEntity pet) {
        return petService.save(pet);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public PetEntity updatePet(@PathVariable Long id, @RequestBody PetEntity pet) {
        return petService.update(id, pet);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deleteById(id);
    }
}

