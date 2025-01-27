package com.ufc.pix.service;

import com.ufc.pix.dto.ViewGenerateReportsDto;
import com.ufc.pix.dto.ViewUserDto;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface GenerateReportsService {
    Optional<ViewGenerateReportsDto> generateByUserId(UUID userId, LocalDateTime startDate, LocalDateTime endDate);
}
