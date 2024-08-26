package com.project.Animal_Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Animal_Shelter.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}