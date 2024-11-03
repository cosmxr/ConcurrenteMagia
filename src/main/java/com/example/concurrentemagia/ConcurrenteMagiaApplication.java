package com.example.concurrentemagia;

import com.example.concurrentemagia.model.MagicalEvent;
import com.example.concurrentemagia.service.MagicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConcurrenteMagiaApplication {

    @Autowired
    private MagicalEventService magicalEventService;

    public static void main(String[] args) {
        SpringApplication.run(ConcurrenteMagiaApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            resetMagicalEvents();
        };
    }

    private void resetMagicalEvents() {
        MagicalEvent wizard = magicalEventService.findById(10L);
        if (wizard != null) {
            wizard.setName("Wizard");
            wizard.setUserHealth(100); // Vida jugador
            wizard.setChallengerHealth(100); // Vida oponente
            wizard.setTurn(1); // contador de turnos
            wizard.setLevel(1); // nivel de desafio
            wizard.setDefensePercentage(15); // Porcentaje de defensa
            wizard.setAttackPoints(10); // Puntos de ataque
            magicalEventService.save(wizard);
        }
        MagicalEvent troll = magicalEventService.findById(8L);
        if (troll != null) {
            troll.setName("Troll");
            troll.setUserHealth(100);
            troll.setChallengerHealth(200);
            troll.setTurn(1);
            troll.setLevel(2);
            troll.setDefensePercentage(25);
            troll.setAttackPoints(20);
            magicalEventService.save(troll);
        }
        MagicalEvent dragon = magicalEventService.findById(9L);
        if (dragon != null) {
            dragon.setName("Dragon");
            dragon.setUserHealth(100);
            dragon.setChallengerHealth(300);
            dragon.setTurn(1);
            dragon.setLevel(3);
            dragon.setDefensePercentage(35);
            dragon.setAttackPoints(30);
            magicalEventService.save(dragon);
        }
    }
}