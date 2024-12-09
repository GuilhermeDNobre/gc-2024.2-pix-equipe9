package com.ufc.pix.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class StatementDto {
    @NotNull(message = "Sender account id is required")
    private UUID senderAccountId;

    @NotNull(message = "Receiver account id is required")
    private UUID receiverAccountId;
}
