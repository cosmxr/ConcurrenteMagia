package com.example.concurrentemagia.model;

import jakarta.persistence.*;

//Clase que representa un hechizo y representa una entidad en la base de datos
@Entity
public class Spell {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private SpellType type;

    //Getters & Setters
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public SpellType getType() {
        return type;
    }
    public void setType(SpellType type) {
        this.type = type;
    }
}