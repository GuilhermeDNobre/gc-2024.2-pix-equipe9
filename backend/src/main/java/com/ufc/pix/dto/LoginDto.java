package com.ufc.pix.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginDto(
        @Schema(example = "usuario@gmail.com") String email,
        @Schema(example = "qwe123") String password
) {}
