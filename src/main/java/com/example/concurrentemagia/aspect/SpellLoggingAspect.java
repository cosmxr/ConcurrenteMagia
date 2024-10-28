package com.example.concurrentemagia.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpellLoggingAspect {

    @Pointcut("execution(* com.example.concurrentemagia.service.SpellService.save(..))")
    public void saveSpell() {}

    @After("saveSpell()")
    public void logSaveSpell() {
        System.out.println("A spell was saved.");
    }

    @Pointcut("execution(* com.example.concurrentemagia.service.SpellService.deleteById(..))")
    public void deleteSpell() {}

    @After("deleteSpell()")
    public void logDeleteSpell() {
        System.out.println("A spell was deleted.");
    }
}