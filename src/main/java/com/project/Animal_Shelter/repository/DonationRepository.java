package com.project.Animal_Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Animal_Shelter.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}