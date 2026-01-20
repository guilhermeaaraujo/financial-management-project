package com.guilherme.financialmanagement.repositories;

import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.Category;
import com.guilherme.financialmanagement.domain.Transaction;
import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.domain.enums.AccountType;
import com.guilherme.financialmanagement.domain.enums.CategoryType;
import com.guilherme.financialmanagement.domain.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should return a list of transactions")
    void findTransactionsByAccountId() {
        User user = new User("Test", "test@gmail.com", "password", UserRole.USER);
        this.entityManager.persist(user);

        Account account = new Account("Test account", 00.0, AccountType.SAVINGS, user);
        this.entityManager.persist(account);

        Category category = new Category("Test", CategoryType.EXPENSE);
        this.entityManager.persist(category);

        Transaction transaction = new Transaction(100.0, LocalDate.now(), account, category);
        this.entityManager.persist(transaction);

        this.entityManager.flush();

        List<Transaction> result = transactionRepository.findTransactionsByAccountId(account.getId());

        assertThat(result).hasSize(1);

    }
}