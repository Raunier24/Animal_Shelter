package com.project.Animal_Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Animal_Shelter.model.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserId(Long userId);  // Nuevo método para filtrar donaciones por usuario
}