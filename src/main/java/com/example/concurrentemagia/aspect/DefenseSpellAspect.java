package com.example.concurrentemagia.aspect;

import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.model.SpellType;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DefenseSpellAspect {

    @Pointcut("execution(* com.example.concurrentemagia.service.SpellService.save(..)) && args(spell)")
    public void saveSpell(Spell spell) {}

    @AfterReturning(pointcut = "saveSpell(spell)", returning = "result")
    public void logDefenseSpell(Spell spell, Object result) {
        if (spell.getType() == SpellType.DEFENSE) {
            System.out.println("A spell of type 'DEFENSE' was saved: " + spell.getName());
        }
    }
}