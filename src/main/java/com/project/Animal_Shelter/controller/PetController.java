package com.project.Animal_Shelter.controller;

import com.project.Animal_Shelter.model.Pet;
import com.project.Animal_Shelter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PostMapping
    public Pet addPet(@RequestBody Pet pet) {
        return petService.addPet(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        return petService.updatePet(id, petDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
}