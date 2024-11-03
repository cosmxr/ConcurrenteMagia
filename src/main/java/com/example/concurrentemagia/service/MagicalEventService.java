package com.example.concurrentemagia.service;

import com.example.concurrentemagia.model.MagicalEvent;
import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.repository.MagicalEventRepository;
import com.example.concurrentemagia.repository.SpellRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagicalEventService {

    private final MagicalEventRepository magicalEventRepository;
    private final SpellRepository spellRepository;

    public MagicalEventService(MagicalEventRepository magicalEventRepository, SpellRepository spellRepository) {
        this.magicalEventRepository = magicalEventRepository;
        this.spellRepository = spellRepository;
    }

    //guarda eventos magicos
    public void save(MagicalEvent event) {
        validateMagicalEvent(event);
        magicalEventRepository.save(event);
    }

    //buca eventos por id
    public MagicalEvent findById(Long id) {
        return magicalEventRepository.findById(id).orElse(null);
    }
    public List<Spell> getAllSpells() {
        return spellRepository.findAll();
    }
    public List<MagicalEvent> getAllEvents() {
        return magicalEventRepository.findAll();
    }
//recoge el evento actual
    public MagicalEvent getCurrentEvent() {
       return findById(10L);
    }

    //interaccion de hechizos por turnos
    public String applySpell(MagicalEvent event, Spell spell) {
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
        } else { // turno del oponente
            event.setUserHealth(event.getUserHealth() - event.getAttackPoints());
        }
        event.setTurn(event.getTurn() + 1);

        if (event.getChallengerHealth() <= 0) {
            if (event.getLevel() == 3){
                return "finalVictory";
            }
            advanceToNextLevel(event);
            save(event);
            return "victory";
        } else if (event.getUserHealth() <= 0) {
            event.setLevel(-1); // derrota
            save(event);
            return "defeat";
        }

        save(event);
        return "challenge";
    }
//se avanza al siguiente nivel cuando ya se derrota al enemigo
    private void advanceToNextLevel(MagicalEvent event) {
        switch (event.getLevel()) {
            case 1:
                event.setLevel(2);
                event.setChallengerHealth(200);
                event.setName("Troll");
                break;
            case 2:
                event.setLevel(3);
                event.setChallengerHealth(300);
                event.setName("Dragon");
                break;
            case 3:
                event.setLevel(4); // victoria final
                break;
            default:
                break;
        }
        event.setTurn(1);
    }
 //validacion de evento magico
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