package com.example.concurrentemagia.aspect;

import com.example.concurrentemagia.model.Spell;
import com.example.concurrentemagia.model.SpellType;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//Aspecto personalizado para el log de la creación de hechizos de tipo 'DEFENSE'
@Aspect
@Component
public class DefenseSpellAspect {

    //Pointcut expression que intercepta la ejecución del metodo 'save' de la clase 'SpellService' que recibe un objeto de tipo 'Spell'
    @Pointcut("execution(* com.example.concurrentemagia.service.SpellService.save(..)) && args(spell)")
    public void saveSpell(Spell spell) {}

    @AfterReturning(pointcut = "saveSpell(spell)", returning = "result")
    public void logDefenseSpell(Spell spell, Object result) {
        if (spell.getType() == SpellType.DEFENSE) {
            System.out.println("Hechizo del tipo 'DEFENSE' ha sido añadido: " + spell.getName());
        }
    }
}