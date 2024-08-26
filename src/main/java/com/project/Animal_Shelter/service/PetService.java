package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Pet;
import com.project.Animal_Shelter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
        pet.setName(petDetails.getName());
        pet.setType(petDetails.getType());
        pet.setBreed(petDetails.getBreed());
        pet.setAge(petDetails.getAge());
        pet.setDescription(petDetails.getDescription());
        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}