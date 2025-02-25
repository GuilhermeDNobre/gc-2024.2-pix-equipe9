package com.ufc.pix.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    ACTIVE("Ativo"),
    INACTIVE("Inativo"),
    BLOCKED("Bloqueado"),
    SUSPENDED("Suspenso"),
    DELETED("Deletado"),
    DISABLED("Desabilitado");

    private final String description;
}
