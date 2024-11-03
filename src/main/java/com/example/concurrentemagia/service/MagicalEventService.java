package com.example.concurrentemagia.service;

import com.example.concurrentemagia.model.MagicalEvent;
import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.repository.MagicalEventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MagicalEventService {

    private final MagicalEventRepository magicalEventRepository;
    private final SpellService spellService;

    public MagicalEventService(MagicalEventRepository magicalEventRepository, SpellService spellService) {
        this.magicalEventRepository = magicalEventRepository;
        this.spellService = spellService;
        initializeRandomEvents();
    }

    public List<MagicalEvent> findAll() {
        return magicalEventRepository.findAll();
    }

    public MagicalEvent save(MagicalEvent magicalEvent) {
        return magicalEventRepository.save(magicalEvent);
    }

    public void deleteById(Long id) {
        magicalEventRepository.deleteById(id);
    }

    public MagicalEvent getRandomEvent() {
        List<MagicalEvent> events = magicalEventRepository.findAll();
        if (events.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return events.get(random.nextInt(events.size()));
    }

    public MagicalEvent findById(Long id) {
        return magicalEventRepository.findById(id).orElse(null);
    }

    public void applySpell(MagicalEvent event, Spell spell) {
        // Challenger attacks first
        event.setUserHealth(event.getUserHealth() - event.getChallengerHealth());

        // User uses spell
        switch (spell.getType()) {
            case ATTACK:
                event.setChallengerHealth(event.getChallengerHealth() - spell.getAttackDamage());
                break;
            case HEALING:
                event.setUserHealth(event.getUserHealth() + spell.getHealingAmount());
                break;
            case DEFENSE:
                event.setChallengerHealth(event.getChallengerHealth() - spell.getAttackBoost());
                event.setUserHealth(event.getUserHealth() - (event.getChallengerHealth() * (100 - spell.getDefensePercentage()) / 100));
                break;
        }

        // Save the updated event
        magicalEventRepository.save(event);
    }

    private void initializeRandomEvents() {
        if (magicalEventRepository.count() == 0) {
            List<Spell> spells = spellService.findAll();
            List<MagicalEvent> events = new ArrayList<>();

            MagicalEvent event1 = new MagicalEvent();
            event1.setName("Se acerca un troll!!");
            event1.setDescription("Aparece un troll salvaje, y comienza a atacar");
            event1.setChallengerHealth(100);
            event1.setUserHealth(100);
            event1.setSpells(spells);
            events.add(event1);

            MagicalEvent event2 = new MagicalEvent();
            event2.setName("Un dragón está sobrevolando la zona");
            event2.setDescription("Un dragón vuela por encima, escupiendo fuego de manera descontrolada");
            event2.setChallengerHealth(200);
            event2.setUserHealth(100);
            event2.setSpells(spells);
            events.add(event2);

            MagicalEvent event3 = new MagicalEvent();
            event3.setName("Duelo de magos");
            event3.setDescription("Has sido retado a un duelo de magos");
            event3.setChallengerHealth(150);
            event3.setUserHealth(100);
            event3.setSpells(spells);
            events.add(event3);

            magicalEventRepository.saveAll(events);
        }
    }
}