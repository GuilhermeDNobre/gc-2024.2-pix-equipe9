package com.ufc.pix.dto;

import com.ufc.pix.model.User;

import java.time.LocalDate;
import java.util.UUID;

public record GetUserInfoDto(
        UUID id,
        String name,
        String email,
        String cpf,
        LocalDate birthDate,
        String access
) {
    public static GetUserInfoDto fromUser(User user) {
        return new GetUserInfoDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getBirthDate(),
                user.getAccess()
        );
    }
}
