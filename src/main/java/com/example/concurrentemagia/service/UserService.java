package com.example.concurrentemagia.service;

import com.example.concurrentemagia.model.User;
import com.example.concurrentemagia.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//Servicio para los usuarios
@Service
public class UserService implements UserDetailsService {

    //Repositorio de usuarios
    private final UserRepository userRepository;
    //Codificador de contraseñas
    private final PasswordEncoder passwordEncoder;

    //Constructor
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Metodo para guardar un usuario
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    //Metodo para buscar un usuario por su nombre de usuario
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //Metodo para cargar un usuario por su nombre de usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}