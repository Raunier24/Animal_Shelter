package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Pet;
import com.project.Animal_Shelter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    // Obtener todas las mascotas
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Obtener una mascota por ID
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    // Agregar una nueva mascota
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    // Actualizar una mascota existente
    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        pet.setName(petDetails.getName());
        pet.setType(petDetails.getType());
        pet.setBreed(petDetails.getBreed());
        pet.setAge(petDetails.getAge());
        pet.setDescription(petDetails.getDescription());

        return petRepository.save(pet);
    }

    // Eliminar una mascota por ID
    public void deletePet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new RuntimeException("Mascota no encontrada");
        }
    }
}
