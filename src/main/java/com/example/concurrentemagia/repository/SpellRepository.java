package com.example.concurrentemagia.repository;

import com.example.concurrentemagia.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {
    List<Spell> findByUserId(Long userId); // Nuevo método para buscar hechizos por userId
}