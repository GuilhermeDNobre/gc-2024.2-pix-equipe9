package com.ufc.pix.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateDailyLimitDto {

    @NotNull
    @Size(min = 1)
    @Schema(example = "2000.00")
    private Double dailyValueLimit;
}