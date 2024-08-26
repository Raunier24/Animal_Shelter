package com.project.Animal_Shelter.controller;

import com.project.Animal_Shelter.model.Donation;
import com.project.Animal_Shelter.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    @PostMapping
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }

    @PutMapping("/{id}")
    public Donation updateDonation(@PathVariable Long id, @RequestBody Donation donationDetails) {
        return donationService.updateDonation(id, donationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
    }
}
