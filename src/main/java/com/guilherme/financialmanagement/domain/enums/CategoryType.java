package com.guilherme.financialmanagement.domain.enums;

public enum CategoryType {

    EXPENSE("EXPENSE"),
    INCOME("INCOME");

    private String type;

    CategoryType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
