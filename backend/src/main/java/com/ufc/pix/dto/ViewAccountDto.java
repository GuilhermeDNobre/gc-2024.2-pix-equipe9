package com.ufc.pix.dto;

import com.ufc.pix.enumeration.AccountStatus;
import com.ufc.pix.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewAccountDto {

    private UUID id;
    private Integer agency;
    private Integer number;
    private AccountType type;
    private Double balance;
    private AccountStatus status;
    private ViewUserDto user;
    private List<ViewPixKeyDto> pixKeys;
}
