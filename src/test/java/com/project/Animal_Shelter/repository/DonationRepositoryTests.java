/*package com.project.Animal_Shelter.repository;

import com.project.Animal_Shelter.model.Donation;
import com.project.Animal_Shelter.model.User;
import com.project.Animal_Shelter.repository.DonationRepository;
import com.project.Animal_Shelter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DonationRepositoryTests {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserId() {
        // Crear y guardar un usuario para la prueba
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        userRepository.save(user1);

        // Crear otro usuario
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        userRepository.save(user2);

        // Crear donaciones asociadas a user1 y user2
        Donation donation1 = new Donation();
        // Aquí asegúrate de usar la lógica que ya funciona en tu código para asociar el usuario a la donación
        donationRepository.save(donation1);  // Guarda la donación

        Donation donation2 = new Donation();
        // Asociar la segunda donación a user2 si es necesario
        donationRepository.save(donation2);  // Guarda la donación

        // Ejecuta el método findByUserId
        List<Donation> donations = donationRepository.findByUserId(user1.getId());

        // Verifica los resultados, ajusta los métodos de acuerdo a tu modelo actual
        assertThat(donations).hasSize(1);  // Debe haber una donación para user1
        assertThat(donations.get(0).getUser().getId()).isEqualTo(user1.getId());
    }
}*/