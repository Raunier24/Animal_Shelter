package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Donation;
import com.project.Animal_Shelter.model.User;
import com.project.Animal_Shelter.repository.DonationRepository;
import com.project.Animal_Shelter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public Donation addDonation(Long userId, Donation donation) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        donation.setUser(user);
        return donationRepository.save(donation);
    }

    public Donation updateDonation(Long id, Donation donationDetails) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        donation.setDonorName(donationDetails.getDonorName());
        donation.setAmount(donationDetails.getAmount());
        donation.setMessage(donationDetails.getMessage());
        donation.setUser(donationDetails.getUser()); // Assuming you want to update the user
        return donationRepository.save(donation);
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}