package com.ufc.pix.dto;

import java.time.LocalDate;
import java.util.UUID;

public record GetUserInfoDto(
        UUID id,
        String name,
        String email,
        String taxId,
        LocalDate birthDate,
        String access
) {}