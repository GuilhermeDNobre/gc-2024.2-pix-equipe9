package com.ufc.pix.dto;

import com.ufc.pix.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchTrasactionDto {

    private UUID id;
    private Double valueStart;
    private Double valueEnd;
    private LocalDateTime transactionDateStart;
    private LocalDateTime transactionDateEnd;
    private UUID senderId;
    private UUID receiverId;

}
