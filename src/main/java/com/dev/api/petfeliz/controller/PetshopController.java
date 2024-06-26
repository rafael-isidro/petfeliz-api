package com.dev.api.petfeliz.controller;

import com.dev.api.petfeliz.entity.PetshopEntity;
import com.dev.api.petfeliz.service.PetshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petshop")
public class PetshopController {

    @Autowired
    private PetshopService petshopService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PetshopEntity> getAllPetshops() {
        return petshopService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PetshopEntity getPetshopById(@PathVariable Long id) {
        return petshopService.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PetshopEntity createPetshop(@RequestBody PetshopEntity petshop) {
        return petshopService.createPetshop(petshop);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public PetshopEntity updatePetshop(@PathVariable Long id, @RequestBody PetshopEntity petshop) {
        return petshopService.update(id, petshop);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePetshop(@PathVariable Long id) {
        petshopService.deleteById(id);
    }
}
