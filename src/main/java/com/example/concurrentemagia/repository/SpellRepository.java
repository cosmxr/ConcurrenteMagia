package com.example.concurrentemagia.repository;

import com.example.concurrentemagia.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio para los hechizos
@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {}