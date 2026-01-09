package com.project.financialmanagement.config;

import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "João", "joao@gmail.com");
        User u2 = new User(null, "Maria", "maria@gmail.com");

        userRepository.saveAll(Arrays.asList(u1, u2));

    }
}
