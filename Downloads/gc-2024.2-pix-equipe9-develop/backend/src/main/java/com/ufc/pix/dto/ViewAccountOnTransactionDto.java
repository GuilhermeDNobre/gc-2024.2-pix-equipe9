package com.ufc.pix.dto;

import com.ufc.pix.enumeration.AccountStatus;
import com.ufc.pix.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewAccountOnTransactionDto {

    private UUID accountId;
    private Integer agency;
    private Integer number;
    private AccountType type;
    private AccountStatus status;
    private UUID userId;
    private List<ViewPixKeyDto> pixKeys;

}
