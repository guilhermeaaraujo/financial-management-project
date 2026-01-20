package com.guilherme.financialmanagement.repositories;

import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.domain.enums.AccountType;
import com.guilherme.financialmanagement.domain.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should return an list of accounts")
    void findByUserId() {
        User user = new User("Test", "test@gmail.com", "password", UserRole.USER);
        entityManager.persist(user);

        Account account = new Account("Test account", 00.0, AccountType.SAVINGS, user);

        this.entityManager.persist(account);

        this.entityManager.flush();

        List<Account> result = this.accountRepository.findByUserId(user.getId());

        assertThat(result).hasSize(1).allMatch(acc -> acc.getUser().getId().equals(user.getId()));
    }
}