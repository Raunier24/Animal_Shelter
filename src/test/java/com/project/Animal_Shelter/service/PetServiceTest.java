package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Pet;
import com.project.Animal_Shelter.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPets() {
        // Arrange
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        List<Pet> pets = Arrays.asList(pet1, pet2);
        when(petRepository.findAll()).thenReturn(pets);

        // Act
        List<Pet> result = petService.getAllPets();

        // Assert
        assertEquals(pets, result);
    }

    @Test
    public void testGetPetById_Success() {
        // Arrange
        Long petId = 1L;
        Pet pet = new Pet();
        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));

        // Act
        Pet result = petService.getPetById(petId);

        // Assert
        assertEquals(pet, result);
    }

    @Test
    public void testGetPetById_NotFound() {
        // Arrange
        Long petId = 1L;
        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            petService.getPetById(petId);
        });
        assertEquals("Mascota no encontrada con ID " + petId, exception.getMessage());
    }

    @Test
    public void testAddPet() {
        // Arrange
        Pet pet = new Pet();
        when(petRepository.save(pet)).thenReturn(pet);

        // Act
        Pet result = petService.addPet(pet);

        // Assert
        assertEquals(pet, result);
    }

    @Test
    public void testUpdatePet_Success() {
        // Arrange
        Long petId = 1L;
        Pet existingPet = new Pet();
        Pet petDetails = new Pet();
        petDetails.setName("Buddy");
        petDetails.setType("Dog");
        petDetails.setBreed("Labrador");
        petDetails.setAge(5);
        petDetails.setDescription("Friendly dog");

        when(petRepository.findById(petId)).thenReturn(Optional.of(existingPet));
        when(petRepository.save(existingPet)).thenReturn(existingPet);

        // Act
        Pet result = petService.updatePet(petId, petDetails);

        // Assert
        assertEquals(existingPet, result);
        assertEquals("Buddy", existingPet.getName());
        assertEquals("Dog", existingPet.getType());
        assertEquals("Labrador", existingPet.getBreed());
        assertEquals(5, existingPet.getAge());
        assertEquals("Friendly dog", existingPet.getDescription());
    }

    @Test
    public void testUpdatePet_NotFound() {
        // Arrange
        Long petId = 1L;
        Pet petDetails = new Pet();
        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            petService.updatePet(petId, petDetails);
        });
        assertEquals("Mascota no encontrada con ID " + petId, exception.getMessage());
    }

    @Test
    public void testDeletePet_Success() {
        // Arrange
        Long petId = 1L;
        when(petRepository.existsById(petId)).thenReturn(true);
        doNothing().when(petRepository).deleteById(petId);

        // Act
        petService.deletePet(petId);

        // Assert
        verify(petRepository).deleteById(petId);
    }

    @Test
    public void testDeletePet_NotFound() {
        // Arrange
        Long petId = 1L;
        when(petRepository.existsById(petId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            petService.deletePet(petId);
        });
        assertEquals("Mascota no encontrada con ID " + petId, exception.getMessage());
    }
}