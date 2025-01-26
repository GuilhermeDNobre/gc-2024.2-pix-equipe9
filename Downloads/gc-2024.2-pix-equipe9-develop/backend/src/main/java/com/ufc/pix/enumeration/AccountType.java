package com.ufc.pix.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    POUPANCA("Poupança"),
    CORRENTE("Corrente");

    private final String description;

}
