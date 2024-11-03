package com.example.concurrentemagia.aspect;

import com.example.concurrentemagia.model.MagicalEvent;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MagicalEventLoggingAspect {

    // Pointcut para interceptar la ejecución del método 'save' de la clase 'MagicalEventService'
    @Pointcut("execution(* com.example.concurrentemagia.service.MagicalEventService.save(..)) && args(event)")
    public void saveMagicalEvent(MagicalEvent event) {}

    // AfterReturning advice para registrar cuando se guarda un evento mágico
    @AfterReturning(pointcut = "saveMagicalEvent(event)", returning = "result")
    public void logSaveMagicalEvent(MagicalEvent event, Object result) {
        System.out.println("Evento mágico guardado: " + event.getName());
    }
}