package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Donation;
import com.project.Animal_Shelter.model.User;
import com.project.Animal_Shelter.repository.DonationRepository;
import com.project.Animal_Shelter.repository.UserRepository;
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

public class DonationServiceTest {

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DonationService donationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDonations() {
        // Arrange
        Donation donation1 = new Donation();
        Donation donation2 = new Donation();
        List<Donation> donations = Arrays.asList(donation1, donation2);
        when(donationRepository.findAll()).thenReturn(donations);

        // Act
        List<Donation> result = donationService.getAllDonations();

        // Assert
        assertEquals(donations, result);
    }

    @Test
    public void testGetDonationById_Success() {
        // Arrange
        Long donationId = 1L;
        Donation donation = new Donation();
        when(donationRepository.findById(donationId)).thenReturn(Optional.of(donation));

        // Act
        Donation result = donationService.getDonationById(donationId);

        // Assert
        assertEquals(donation, result);
    }

    @Test
    public void testGetDonationById_NotFound() {
        // Arrange
        Long donationId = 1L;
        when(donationRepository.findById(donationId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            donationService.getDonationById(donationId);
        });
        assertEquals("Donación no encontrada con ID " + donationId, exception.getMessage());
    }

    @Test
    public void testAddDonation_Success() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        Donation donation = new Donation();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(donationRepository.save(donation)).thenReturn(donation);

        // Act
        Donation result = donationService.addDonation(userId, donation);

        // Assert
        assertEquals(donation, result);
        assertEquals(user, donation.getUser()); // Verificar que el usuario se ha asociado
    }

    @Test
    public void testAddDonation_UserNotFound() {
        // Arrange
        Long userId = 1L;
        Donation donation = new Donation();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            donationService.addDonation(userId, donation);
        });
        assertEquals("Usuario no encontrado con ID " + userId, exception.getMessage());
    }

    @Test
    public void testGetDonationsByUserId() {
        // Arrange
        Long userId = 1L;
        Donation donation1 = new Donation();
        Donation donation2 = new Donation();
        List<Donation> donations = Arrays.asList(donation1, donation2);
        when(donationRepository.findByUserId(userId)).thenReturn(donations);

        // Act
        List<Donation> result = donationService.getDonationsByUserId(userId);

        // Assert
        assertEquals(donations, result);
    }

    @Test
    public void testUpdateDonation_Success() {
        // Arrange
        Long donationId = 1L;
        Donation existingDonation = new Donation();
        Donation donationDetails = new Donation();
        donationDetails.setDonorName("John Doe");
        donationDetails.setAmount(100.0);
        donationDetails.setMessage("Test message");
        when(donationRepository.findById(donationId)).thenReturn(Optional.of(existingDonation));
        when(donationRepository.save(existingDonation)).thenReturn(existingDonation);

        // Act
        Donation result = donationService.updateDonation(donationId, donationDetails);

        // Assert
        assertEquals(existingDonation, result);
        assertEquals("John Doe", existingDonation.getDonorName());
        assertEquals(100.0, existingDonation.getAmount());
        assertEquals("Test message", existingDonation.getMessage());
    }

    @Test
    public void testUpdateDonation_NotFound() {
        // Arrange
        Long donationId = 1L;
        Donation donationDetails = new Donation();
        when(donationRepository.findById(donationId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            donationService.updateDonation(donationId, donationDetails);
        });
        assertEquals("Donación no encontrada con ID " + donationId, exception.getMessage());
    }

    @Test
    public void testDeleteDonation_Success() {
        // Arrange
        Long donationId = 1L;
        when(donationRepository.existsById(donationId)).thenReturn(true);
        doNothing().when(donationRepository).deleteById(donationId);

        // Act
        donationService.deleteDonation(donationId);

        // Assert
        verify(donationRepository).deleteById(donationId);
    }

    @Test
    public void testDeleteDonation_NotFound() {
        // Arrange
        Long donationId = 1L;
        when(donationRepository.existsById(donationId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            donationService.deleteDonation(donationId);
        });
        assertEquals("Donación no encontrada con ID " + donationId, exception.getMessage());
    }
}