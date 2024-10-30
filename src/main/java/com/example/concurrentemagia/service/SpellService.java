package com.example.concurrentemagia.service;

import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.repository.SpellRepository;
import org.springframework.stereotype.Service;
import java.util.List;

//Servicio para los hechizos
@Service
public class SpellService {

    //Repositorio de hechizos
    private final SpellRepository spellRepository;

    //Constructor
    public SpellService(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    //Metodo para buscar todos los hechizos
    public List<Spell> findAll() {
        return spellRepository.findAll();
    }

    //Metodo para guardar un hechizo
    public Spell save(Spell spell) {
        return spellRepository.save(spell);
    }

    //Metodo para eliminar un hechizo por su id
    public void deleteById(Long id) {
        spellRepository.deleteById(id);
    }
}