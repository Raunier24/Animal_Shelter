package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Donation;
import com.project.Animal_Shelter.model.User;
import com.project.Animal_Shelter.repository.DonationRepository;
import com.project.Animal_Shelter.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    // Obtener todas las donaciones
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // Obtener donación por ID con excepción si no se encuentra
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Donación no encontrada con ID " + id));
    }

    // Agregar una donación y asociarla con un usuario
    public Donation addDonation(Long userId, Donation donation) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID " + userId));
        donation.setUser(user); // Asociar el usuario a la donación
        return donationRepository.save(donation);
    }

    // Actualizar una donación existente
    public Donation updateDonation(Long id, Donation donationDetails) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Donación no encontrada con ID " + id));

        donation.setDonorName(donationDetails.getDonorName());
        donation.setAmount(donationDetails.getAmount());
        donation.setMessage(donationDetails.getMessage());
        donation.setUser(donationDetails.getUser()); // Asumimos que también quieres actualizar el usuario

        return donationRepository.save(donation);
    }

    // Eliminar una donación por ID
    public void deleteDonation(Long id) {
        if (!donationRepository.existsById(id)) {
            throw new EntityNotFoundException("Donación no encontrada con ID " + id);
        }
        donationRepository.deleteById(id);
    }
}