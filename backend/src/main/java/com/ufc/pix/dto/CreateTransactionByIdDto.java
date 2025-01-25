package com.ufc.pix.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateTransactionByIdDto {
    @NotNull(message = "Sender account id is required")
    private UUID sender_id;

    @NotNull(message = "Receiver account id is required")
    private UUID receiver_id;

    @NotNull(message = "Value is required")
    private Double value;

}
