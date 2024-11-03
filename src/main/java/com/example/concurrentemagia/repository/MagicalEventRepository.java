package com.example.concurrentemagia.repository;

import com.example.concurrentemagia.model.MagicalEvent;
import com.example.concurrentemagia.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagicalEventRepository extends JpaRepository<MagicalEvent, Long> {
    List<MagicalEvent> findBySpellsContaining(Spell spell);
}