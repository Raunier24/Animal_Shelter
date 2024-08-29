package com.project.Animal_Shelter.controller;

import com.project.Animal_Shelter.model.Pet;
import com.project.Animal_Shelter.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

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
        when(petService.getAllPets()).thenReturn(pets);

        // Act
        List<Pet> result = petController.getAllPets();

        // Assert
        assertEquals(pets, result);
    }

    @Test
    public void testGetPetById() {
        // Arrange
        Long petId = 1L;
        Pet pet = new Pet();
        when(petService.getPetById(petId)).thenReturn(pet);

        // Act
        Pet result = petController.getPetById(petId);

        // Assert
        assertEquals(pet, result);
    }

    @Test
    public void testAddPet() {
        // Arrange
        Pet pet = new Pet();
        when(petService.addPet(pet)).thenReturn(pet);

        // Act
        Pet result = petController.addPet(pet);

        // Assert
        assertEquals(pet, result);
    }

    @Test
    public void testUpdatePet() {
        // Arrange
        Long petId = 1L;
        Pet petDetails = new Pet();
        Pet updatedPet = new Pet();
        when(petService.updatePet(petId, petDetails)).thenReturn(updatedPet);

        // Act
        Pet result = petController.updatePet(petId, petDetails);

        // Assert
        assertEquals(updatedPet, result);
    }

    @Test
    public void testDeletePet() {
        // Arrange
        Long petId = 1L;
        doNothing().when(petService).deletePet(petId);

        // Act
        petController.deletePet(petId);

        // Assert
        verify(petService).deletePet(petId);
    }
}