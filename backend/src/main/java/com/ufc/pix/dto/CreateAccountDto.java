package com.ufc.pix.dto;

import com.ufc.pix.enumeration.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountDto {

    @NotNull(message = "User ID is required")
    private UUID UserId;

    @NotNull(message = "Account Agency is required")
    private Integer agency;

    @NotNull(message = "Account Number is required")
    private Integer number;

    @NotNull(message = "Account Type is required")
    private AccountType type;

    @NotNull(message = "Account balance is required")
    private Double balance;

    @NotNull(message = "Password is required")
    private String password;

}