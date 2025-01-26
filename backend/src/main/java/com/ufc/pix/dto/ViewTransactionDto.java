package com.ufc.pix.dto;

import com.ufc.pix.enumeration.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewTransactionDto {

    private UUID id;
    private Double transferValue;
    private TransactionStatus status;
    private LocalDate sendDate;
    private LocalDateTime finishedAt;
    private ViewAccountOnTransactionDto sender;
    private ViewAccountOnTransactionDto receiver;

}
