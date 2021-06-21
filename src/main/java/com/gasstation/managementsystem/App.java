package com.gasstation.managementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        System.out.println(passwordEncoder.encode("1234567a"));
    }

}
