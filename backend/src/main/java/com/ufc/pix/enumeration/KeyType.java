package com.ufc.pix.enumeration;

public enum KeyType {
    CPF("CPF"),
    CNPJ("CNPJ"),
    EMAIL("E-mail"),
    PHONE("Telefone"),
    RANDOM("Chave Aleatória");

    private final String description;

    KeyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
