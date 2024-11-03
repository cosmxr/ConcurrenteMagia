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
            createMagicalEvents();
        };
    }

    private void createMagicalEvents() {
        if (magicalEventService.getAllEvents().isEmpty()) {
            MagicalEvent event1 = new MagicalEvent();
            event1.setName("Duel with a wizard");
            event1.setDescription("Level 1: Duel with a wizard.");
            event1.setChallengerHealth(100);
            event1.setUserHealth(100);
            event1.setLevel(1);
            event1.setAttackPoints(10);
            event1.setDefensePercentage(10);
            magicalEventService.save(event1);

            MagicalEvent event2 = new MagicalEvent();
            event2.setName("Battle with a troll");
            event2.setDescription("Level 2: Battle with a troll.");
            event2.setChallengerHealth(200);
            event2.setUserHealth(100);
            event2.setLevel(2);
            event2.setAttackPoints(20);
            event2.setDefensePercentage(20);
            magicalEventService.save(event2);

            MagicalEvent event3 = new MagicalEvent();
            event3.setName("Fight a dragon");
            event3.setDescription("Level 3: Fight a dragon.");
            event3.setChallengerHealth(300);
            event3.setUserHealth(100);
            event3.setLevel(3);
            event3.setAttackPoints(30);
            event3.setDefensePercentage(30);
            magicalEventService.save(event3);
        }
    }
}