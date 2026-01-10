package com.project.financialmanagement.config;

import com.project.financialmanagement.domain.Account;
import com.project.financialmanagement.domain.Category;
import com.project.financialmanagement.domain.Transaction;
import com.project.financialmanagement.domain.User;
import com.project.financialmanagement.domain.enums.AccountType;
import com.project.financialmanagement.domain.enums.OperationType;
import com.project.financialmanagement.repositories.AccountRepository;
import com.project.financialmanagement.repositories.CategoryRepository;
import com.project.financialmanagement.repositories.TransactionRepository;
import com.project.financialmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {

        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();

        User u1 = new User(null, "João", "joao@gmail.com");
        User u2 = new User(null, "Maria", "maria@gmail.com");

        Account acc1 = new Account(null, "João's Savings Account", 1500.0, AccountType.SAVINGS, u1);
        Account acc2 = new Account(null, "Maria's Currents Account", 1500.0, AccountType.CURRENT, u2);

        Category cat1 = new Category(null, "Mensalidade", OperationType.EXPENSE);
        Category cat2 = new Category(null, "Salário", OperationType.INCOME);

        Transaction t1 = new Transaction(null, 100.0, new Date(), cat1 ,acc1);
        Transaction t2 = new Transaction(null, 1500.0, new Date(), cat2, acc2);

        cat1.getTransactions().add(t1);
        cat2.getTransactions().add(t2);

        acc1.getTransactions().add(t1);
        acc2.getTransactions().add(t2);

        u1.getAccounts().add(acc1);
        u2.getAccounts().add(acc2);

        userRepository.saveAll(Arrays.asList(u1, u2));
        accountRepository.saveAll(Arrays.asList(acc1, acc2));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        transactionRepository.saveAll(Arrays.asList(t1, t2));
    }
}
