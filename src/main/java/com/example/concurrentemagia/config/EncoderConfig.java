package com.example.concurrentemagia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Clase de configuración para el encoder de contraseñas
@Configuration
public class EncoderConfig {

    //Bean para el encoder de contraseñas que devuelve un objeto de tipo 'BCryptPasswordEncoder'
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}