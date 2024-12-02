package com.ufc.pix.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateUserDto(

        @Size(max = 100, message = "Name should not exceed 100 characters") String name,


        @Email(message = "Email should be valid") String email,


        @Pattern(regexp = "\\d{11}", message = "Tax ID should have 11 digits") String cpf, //= CPF in Brazil, SSN in the United States, NIF in Portugal

        LocalDate birthDate
) {}
