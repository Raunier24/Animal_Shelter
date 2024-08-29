package com.project.Animal_Shelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @Size(min = 2, max = 20, message = "El nombre de la mascota debe tener entre 2 y 50 caracteres")
    private String name;

    @NotBlank(message = "El tipo de mascota es obligatorio")
    @Size(min = 2, max = 15, message = "El tipo de mascota debe tener entre 2 y 30 caracteres")
    private String type;

    @NotBlank(message = "La raza de la mascota es obligatoria")
    @Size(min = 2, max = 15, message = "La raza de la mascota debe tener entre 2 y 30 caracteres")
    private String breed;

    @NotNull(message = "La edad de la mascota es obligatoria")
    @Min(value = 0, message = "La edad debe ser un valor positivo o cero")
    @Max(value = 30, message = "La edad no puede ser mayor de 30 años")
    private int age;

    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres")
    private String description;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}