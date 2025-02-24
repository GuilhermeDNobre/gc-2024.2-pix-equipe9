package com.ufc.pix.dto;

import com.ufc.pix.enumeration.UserAccess;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Name is mandatory" )
    @Size(max = 100, message = "Name should not exceed 100 characters")
    @Schema(example = "usuario")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Schema(example = "usuario@gmail.com")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
    @Schema(example = "qwe123")
    private String password;
    @NotBlank(message = "Cpf is mandatory")
    @Pattern(regexp = "\\d{11}", message = "Cpf should have 11 digits")
    @Schema(example = "01234567890")
    private String cpf; //= CPF in Brazil, SSN in the United States, NIF in Portugal
    @NotNull(message = "Birth Date is mandatory")
    @Schema(example = "1999-04-10")
    private LocalDate birthDate;
    @Schema(example = "USER")
    private UserAccess access;

}