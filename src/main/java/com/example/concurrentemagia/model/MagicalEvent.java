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
    private int level = 0;
    private int attackPoints = 0; // Default value
    private int defensePercentage = 0;
    private int turn = 1;

    @ManyToMany
    private List<Spell> spells;

    // Getters and Setters
    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePercentage() {
        return defensePercentage;
    }

    public void setDefensePercentage(int defensePercentage) {
        this.defensePercentage = defensePercentage;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
}