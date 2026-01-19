package com.guilherme.financialmanagement.domain.dto;

import com.guilherme.financialmanagement.domain.Account;
import com.guilherme.financialmanagement.domain.User;
import com.guilherme.financialmanagement.domain.enums.AccountType;

public record AccountDTO(Long id, String name, Double balance, AccountType type, UserDTO user) {

    public AccountDTO(Account account, UserDTO user) {
        this(account.getId(), account.getName(), account.getBalance(), account.getType(), user);
    }
}
