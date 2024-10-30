package com.example.concurrentemagia.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//Aspecto generico para el log de eliminacion de hechizos
@Aspect
@Component
public class SpellLoggingAspect {

    //Pointcut expression que intercepta la ejecución del metodo 'deleteById' de la clase 'SpellService'
    @Pointcut("execution(* com.example.concurrentemagia.service.SpellService.deleteById(..))")
    public void deleteSpell() {}

    @After("deleteSpell()")
    public void logDeleteSpell() {
        System.out.println("A spell was deleted.");
    }
}