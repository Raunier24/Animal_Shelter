package com.project.Animal_Shelter.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    // Getters y Setters
}
