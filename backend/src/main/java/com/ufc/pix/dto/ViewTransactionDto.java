package com.ufc.pix.dto;

import com.ufc.pix.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewTransactionDto {

    private UUID id;
    private Double transferValue;
    private LocalDateTime transactionDate;
    private ViewAccountDto sender;
    private ViewAccountDto receiver;
}
