package com.ufc.pix.dto;

import com.ufc.pix.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountDto {

    private Integer agency;
    private Integer number;
    private AccountType type;
    private String password;
}
