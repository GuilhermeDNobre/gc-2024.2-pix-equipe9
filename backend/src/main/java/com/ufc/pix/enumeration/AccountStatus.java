package com.ufc.pix.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    ACTIVE("Ativo"),
    BLOCKED("Bloqueado"),
    SUSPENDED("Suspenso"),
    DELETED("Deletado"),
    DISABLED("Desabilitado"),
    SUSPICIOUS("Suspeita");

    private final String description;
}
