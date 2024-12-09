package com.ufc.pix.dto;

import com.ufc.pix.model.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO{
    @NotNull(message = "User ID is required")
    private UUID idUser;

    @NotBlank(message = "Institution is required")
    private String institution;

    @NotNull(message = "Account Agency is required")
    private Integer accountAgency;

    @NotNull(message = "Account Number is required")
    private Integer accountNumber;

    @NotBlank(message = "Account Type is required")
    private String accountType;

    private Float balance = 0.0f;

    @NotNull(message = "Four Digit Password is required")
    private Integer fourDigitPassword;

    @NotNull(message = "Six Digit Password is required")
    private Integer sixDigitPassword;

}