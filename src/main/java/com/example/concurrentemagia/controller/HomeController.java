package com.example.concurrentemagia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Controlador para la página de inicio
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}