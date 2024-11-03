package com.example.concurrentemagia.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class MagicalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int challengerHealth;
    private int userHealth;

    @ManyToMany
    private List<Spell> spells;

    // Getters and Setters
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

    public int getChallengerHealth() {
        return challengerHealth;
    }

    public void setChallengerHealth(int challengerHealth) {
        this.challengerHealth = challengerHealth;
    }

    public int getUserHealth() {
        return userHealth;
    }

    public void setUserHealth(int userHealth) {
        this.userHealth = userHealth;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
}