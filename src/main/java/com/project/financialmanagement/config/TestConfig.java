package com.project.financialmanagement.config;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.domain.enums.AccountType;
import com.project.financialmanagement.repositories.AccountRepository;
import com.project.financialmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "João", "joao@gmail.com");
        User u2 = new User(null, "Maria", "maria@gmail.com");

        Account acc1 = new Account(null, "João's Savings Account", 1500.0, AccountType.SAVINGS, u1);
        Account acc2 = new Account(null, "Maria's Currents Account", 1500.0, AccountType.CURRENT, u2);

        u1.getAccounts().add(acc1);
        u2.getAccounts().add(acc2);

        userRepository.saveAll(Arrays.asList(u1, u2));
        accountRepository.saveAll(Arrays.asList(acc1, acc2));
    }
}
