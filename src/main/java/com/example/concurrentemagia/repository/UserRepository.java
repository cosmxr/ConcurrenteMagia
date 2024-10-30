package com.example.concurrentemagia.repository;

import com.example.concurrentemagia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio para los usuarios
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}