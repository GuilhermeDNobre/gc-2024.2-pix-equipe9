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
public class CreateTransactionByIdDto {
    @NotNull(message = "Sender account id is required")
    private UUID senderId;

    @NotNull(message = "Receiver account id is required")
    private UUID receiverId;

    @NotNull(message = "Value is required")
    private Double value;

    private LocalDate sendDate;
}
