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
            wizard.setUserHealth(100); // Reset user health
            wizard.setChallengerHealth(100); // Reset challenger health
            wizard.setTurn(1); // Reset turn counter
            wizard.setLevel(1); // Set level to 1
            wizard.setDefensePercentage(15); // Set defense percentage to 15
            wizard.setAttackPoints(10); // Set attack points to 10
            magicalEventService.save(wizard);
        }
        MagicalEvent troll = magicalEventService.findById(8L);
        if (troll != null) {
            troll.setUserHealth(100); // Reset user health
            troll.setChallengerHealth(200); // Reset challenger health
            troll.setTurn(1); // Reset turn counter
            troll.setLevel(2); // Set level to 1
            troll.setDefensePercentage(25); // Set defense percentage to 15
            troll.setAttackPoints(20); // Set attack points to 10
            magicalEventService.save(troll);
        }
        MagicalEvent dragon = magicalEventService.findById(9L);
        if (dragon != null) {
            dragon.setUserHealth(100); // Reset user health
            dragon.setChallengerHealth(300); // Reset challenger health
            dragon.setTurn(1); // Reset turn counter
            dragon.setLevel(3); // Set level to 1
            dragon.setDefensePercentage(35); // Set defense percentage to 15
            dragon.setAttackPoints(30); // Set attack points to 10
            magicalEventService.save(dragon);
        }
    }
}