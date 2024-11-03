package com.example.concurrentemagia.service;

import com.example.concurrentemagia.model.MagicalEvent;
import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.repository.MagicalEventRepository;
import com.example.concurrentemagia.repository.SpellRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SpellService {

    private final SpellRepository spellRepository;
    private final MagicalEventRepository magicalEventRepository;
    private final Random random = new Random();

    public SpellService(SpellRepository spellRepository, MagicalEventRepository magicalEventRepository) {
        this.spellRepository = spellRepository;
        this.magicalEventRepository = magicalEventRepository;
    }

    public List<Spell> findAll() {
        return spellRepository.findAll();
    }

    public Spell save(Spell spell) {
        assignRandomValues(spell);
        validateSpell(spell);
        return spellRepository.save(spell);
    }

    public void deleteById(Long id) {
        Spell spell = spellRepository.findById(id).orElse(null);
        if (spell != null) {
            List<MagicalEvent> events = magicalEventRepository.findBySpellsContaining(spell);
            for (MagicalEvent event : events) {
                event.getSpells().remove(spell);
                magicalEventRepository.save(event);
            }
            spellRepository.deleteById(id);
        }
    }

    public Spell findById(Long spellId) {
        return spellRepository.findById(spellId).orElse(null);
    }

    private void assignRandomValues(Spell spell) {
        switch (spell.getType()) {
            case ATTACK:
                spell.setAttackDamage(random.nextInt(20) + 10); // Attack damage between 10 and 30
                break;
            case HEALING:
                spell.setHealingAmount(random.nextInt(20) + 10); // Healing amount between 10 and 30
                break;
            case DEFENSE:
                spell.setDefensePercentage(random.nextInt(50) + 10); // Defense percentage between 10% and 60%
                spell.setAttackBoost(random.nextInt(10) + 5); // Attack boost between 5 and 15
                break;
            default:
                break;
        }
    }

    private void validateSpell(Spell spell) {
        if (spell.getName() == null || spell.getName().isEmpty()) {
            throw new IllegalArgumentException("Spell name cannot be empty");
        }
        if (spell.getDescription() == null || spell.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Spell description cannot be empty");
        }
        if (spell.getType() == null) {
            throw new IllegalArgumentException("Spell type cannot be null");
        }
        switch (spell.getType()) {
            case ATTACK:
                if (spell.getAttackDamage() <= 0) {
                    throw new IllegalArgumentException("Attack damage must be greater than zero for attack spells");
                }
                break;
            case HEALING:
                if (spell.getHealingAmount() <= 0) {
                    throw new IllegalArgumentException("Healing amount must be greater than zero for healing spells");
                }
                break;
            case DEFENSE:
                if (spell.getDefensePercentage() <= 0 || spell.getDefensePercentage() > 100) {
                    throw new IllegalArgumentException("Defense percentage must be between 1 and 100 for defense spells");
                }
                if (spell.getAttackBoost() < 0) {
                    throw new IllegalArgumentException("Attack boost must be zero or greater for defense spells");
                }
                break;
            default:
                break;
        }
    }
}