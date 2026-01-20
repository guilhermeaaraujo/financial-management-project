package com.guilherme.financialmanagement.repositories;

import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.domain.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should sucessfully get user from db.")
    void findByEmailCase1() {
        User user = new User("Test", "test@gmail.com", "password", UserRole.USER);
        this.entityManager.persist(user);

        Optional<UserDetails> result = this.userRepository.findByEmail(user.getEmail());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get user from db.")
    void findByEmailCase2() {

        Optional<UserDetails> result = this.userRepository.findByEmail("test@gmail.com");

        assertThat(result.isEmpty()).isTrue();
    }

}