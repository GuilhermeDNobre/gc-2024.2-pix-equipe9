package com.ufc.pix.dto;

import com.ufc.pix.enumeration.TransactionStatus;
import com.ufc.pix.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchTrasactionDto {

        private UUID id;
        private Double transferValueStart;
        private Double transferValueEnd;
        private TransactionStatus status;
        private LocalDateTime createdAtStart;
        private LocalDateTime createdAtEnd;
        private LocalDateTime finishedAtStart;
        private LocalDateTime finishedAtEnd;
        private LocalDateTime sendDateStart;
        private LocalDateTime sendDateEnd;
        private UUID senderId;
        private UUID receiverId;

}
