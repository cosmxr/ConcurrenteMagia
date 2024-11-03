package com.example.concurrentemagia.service;

import com.example.concurrentemagia.model.MagicalEvent;
import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.repository.MagicalEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagicalEventService {

    private final MagicalEventRepository magicalEventRepository;

    public MagicalEventService(MagicalEventRepository magicalEventRepository) {
        this.magicalEventRepository = magicalEventRepository;
    }

    public void save(MagicalEvent event) {
        validateMagicalEvent(event);
        magicalEventRepository.save(event);
    }

    public List<MagicalEvent> getAllEvents() {
        return magicalEventRepository.findAll();
    }

    public MagicalEvent getCurrentEvent() {
        return magicalEventRepository.findAll().get(0); // Example implementation
    }

    public void applySpell(MagicalEvent event, Spell spell) {
        if (event.getTurn() % 2 == 0) { // User's turn
            switch (spell.getType()) {
                case ATTACK:
                    event.setChallengerHealth(event.getChallengerHealth() - spell.getAttackDamage());
                    break;
                case HEALING:
                    event.setUserHealth(event.getUserHealth() + spell.getHealingAmount());
                    break;
                case DEFENSE:
                    event.setUserHealth(event.getUserHealth() + spell.getDefensePercentage());
                    break;
                default:
                    break;
            }
        } else { // Challenger's turn
            event.setUserHealth(event.getUserHealth() - event.getAttackPoints());
        }
        event.setTurn(event.getTurn() + 1);
        save(event);
    }
    private void validateMagicalEvent(MagicalEvent event) {
        if (event.getName() == null || event.getName().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty");
        }
        if (event.getDescription() == null || event.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Event description cannot be empty");
        }
        if (event.getChallengerHealth() <= 0) {
            throw new IllegalArgumentException("Challenger health must be greater than zero");
        }
        if (event.getUserHealth() <= 0) {
            throw new IllegalArgumentException("User health must be greater than zero");
        }
        if (event.getLevel() < 0) {
            throw new IllegalArgumentException("Level must be zero or greater");
        }
        if (event.getAttackPoints() < 0) {
            throw new IllegalArgumentException("Attack points must be zero or greater");
        }
        if (event.getDefensePercentage() < 0) {
            throw new IllegalArgumentException("Defense percentage must be zero or greater");
        }
    }
}