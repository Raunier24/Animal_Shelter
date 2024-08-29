package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Pet;
import com.project.Animal_Shelter.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    // Obtener todas las mascotas
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Obtener una mascota por ID con manejo de excepción personalizada
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con ID " + id));
    }

    // Agregar una nueva mascota
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    // Actualizar una mascota existente con manejo de excepción personalizada
    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con ID " + id));

        pet.setName(petDetails.getName());
        pet.setType(petDetails.getType());
        pet.setBreed(petDetails.getBreed());
        pet.setAge(petDetails.getAge());
        pet.setDescription(petDetails.getDescription());

        return petRepository.save(pet);
    }

    // Eliminar una mascota por ID con manejo de excepción personalizada
    public void deletePet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Mascota no encontrada con ID " + id);
        }
    }
}