package com.ufc.pix.dto;

import com.ufc.pix.model.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not exceed 100 characters")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
    @NotBlank(message = "Tax ID is mandatory")
    @Pattern(regexp = "\\d{11}", message = "Tax ID should have 11 digits")
    private String taxId; //= CPF in Brazil, SSN in the United States, NIF in Portugal
    @NotNull(message = "Birth Date is mandatory")
    private LocalDate birthDate;

    public User toModel(){
        return new User(
                getName(),
                getEmail(),
                getPassword(),
                getTaxId(),
                getBirthDate()
        );
    }

}
