package com.project.Animal_Shelter.controller;

import com.project.Animal_Shelter.model.Donation;
import com.project.Animal_Shelter.service.DonationService;
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

public class DonationControllerTest {

    @Mock
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;

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
        when(donationService.getAllDonations()).thenReturn(donations);

        // Act
        List<Donation> result = donationController.getAllDonations();

        // Assert
        assertEquals(donations, result);
    }

    @Test
    public void testGetDonationById() {
        // Arrange
        Long donationId = 1L;
        Donation donation = new Donation();
        when(donationService.getDonationById(donationId)).thenReturn(donation);

        // Act
        Donation result = donationController.getDonationById(donationId);

        // Assert
        assertEquals(donation, result);
    }

    @Test
    public void testGetDonationsByUserId() {
        // Arrange
        Long userId = 1L;
        Donation donation1 = new Donation();
        Donation donation2 = new Donation();
        List<Donation> donations = Arrays.asList(donation1, donation2);
        when(donationService.getDonationsByUserId(userId)).thenReturn(donations);

        // Act
        List<Donation> result = donationController.getDonationsByUserId(userId);

        // Assert
        assertEquals(donations, result);
    }

    @Test
    public void testAddDonation() {
        // Arrange
        Long userId = 1L;
        Donation donation = new Donation();
        when(donationService.addDonation(userId, donation)).thenReturn(donation);

        // Act
        Donation result = donationController.addDonation(userId, donation);

        // Assert
        assertEquals(donation, result);
    }

    @Test
    public void testUpdateDonation() {
        // Arrange
        Long donationId = 1L;
        Donation donationDetails = new Donation();
        Donation updatedDonation = new Donation();
        when(donationService.updateDonation(donationId, donationDetails)).thenReturn(updatedDonation);

        // Act
        Donation result = donationController.updateDonation(donationId, donationDetails);

        // Assert
        assertEquals(updatedDonation, result);
    }

    @Test
    public void testDeleteDonation() {
        // Arrange
        Long donationId = 1L;
        doNothing().when(donationService).deleteDonation(donationId);

        // Act
        donationController.deleteDonation(donationId);

        // Assert
        verify(donationService).deleteDonation(donationId);
    }
}