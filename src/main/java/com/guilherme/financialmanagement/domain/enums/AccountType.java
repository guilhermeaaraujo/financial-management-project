package com.guilherme.financialmanagement.domain.enums;

public enum AccountType {

    SAVINGS("SAVINGS"),
    CURRENT("CURRENT");

    private String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
