package com.ufc.pix.dto;

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
        Boolean active
) {
    public static ViewUserDto fromUser(User user) {
        return new ViewUserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getBirthDate(),
                user.getAccess().getDescription(),
                user.getActive()
        );
    }
}
