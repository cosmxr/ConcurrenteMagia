package com.example.concurrentemagia.aspect;

import com.example.concurrentemagia.model.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActivityAspect {

    @Pointcut("execution(* com.example.concurrentemagia.controller.UserController.registerUser(..)) && args(user)")
    public void registerUser(User user) {}

    @AfterReturning(pointcut = "registerUser(user)", returning = "result")
    public void logRegisterUser(User user, Object result) {
        System.out.println("Usuario registrado: " + user.getUsername());
    }

    @Pointcut("execution(* com.example.concurrentemagia.controller.UserController.login(..))")
    public void loginUser() {}

    @AfterReturning(pointcut = "loginUser()")
    public void logLoginUser() {
        System.out.println("Usuario ha iniciado sesión.");
    }
}