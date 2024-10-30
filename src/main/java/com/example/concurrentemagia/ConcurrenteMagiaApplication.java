package com.example.concurrentemagia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Clase principal de la aplicación
@SpringBootApplication
@RestController
public class ConcurrenteMagiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrenteMagiaApplication.class, args);
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}