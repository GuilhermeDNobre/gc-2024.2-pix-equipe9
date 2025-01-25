package com.ufc.pix.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateTransactionByPixDto {

    @NotNull(message = "Receiver account id is required")
    private UUID receiverPixKey;

    @NotNull(message = "Value is required")
    private Double value;

    private LocalDate sendDate;

}
