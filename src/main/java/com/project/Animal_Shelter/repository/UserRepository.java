package com.project.Animal_Shelter.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Animal_Shelter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Método para verificar si el nombre de usuario ya existe
    boolean existsByUsername(String username);
}
