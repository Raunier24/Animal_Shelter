package com.project.Animal_Shelter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Animal_Shelter.model.Role;
import com.project.Animal_Shelter.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
