package com.ufc.pix.dto;

import com.ufc.pix.model.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO{
    @NotBlank(message = "Institution is required")
    private String institution;

    @NotBlank(message = "Account Agency is required")
    private Integer accountAgency;

    @NotBlank(message = "Account Number is required")
    private Integer accountNumber;

    @NotBlank(message = "Account Type is required")
    private String accountType;

    @NotBlank(message = "Four Digit Password is required")
    @Size(min = 4, max = 4, message = "Four Digit Password must have 4 characters")
    private Integer fourDigitPassword;

    @NotBlank(message = "Six Digit Password is required")
    @Size(min = 6, max = 6, message = "Six Digit Password must have 6 characters")
    private Integer sixDigitPassword;

}