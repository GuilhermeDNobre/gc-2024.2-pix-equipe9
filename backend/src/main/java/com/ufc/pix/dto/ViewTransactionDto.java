package com.ufc.pix.dto;

import com.ufc.pix.enumeration.TransactionStatus;
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
    private ViewAccountDto sender;
    private ViewAccountDto receiver;
    private TransactionStatus status;
    private LocalDateTime sendDate;
    private LocalDateTime finishedAt;

}
