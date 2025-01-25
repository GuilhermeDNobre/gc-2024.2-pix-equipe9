package com.ufc.pix.dto;

import com.ufc.pix.enumeration.UserStatus;
import com.ufc.pix.model.User;

import java.time.LocalDate;
import java.util.UUID;

public record ViewUserDto(
        UUID id,
        String name,
        String email,
        String cpf,
        LocalDate birthDate,
        String access,
        String status
){}
