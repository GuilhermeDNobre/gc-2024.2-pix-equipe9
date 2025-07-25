package com.ufc.pix.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatus {
        PENDING("Pendente"),
        COMPLETED("Finalizado"),
        FAILED("Fracassada"),
        CANCELED("Cancelado");

        private final String description;
}
