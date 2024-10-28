package com.example.concurrentemagia.service;

import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.repository.SpellRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellService {

    private final SpellRepository spellRepository;

    public SpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    public List<Spell> findAll() {
        return spellRepository.findAll();
    }

    public Spell save(Spell spell) {
        return spellRepository.save(spell);
    }

    public void deleteById(Long id) {
        spellRepository.deleteById(id);
    }
}