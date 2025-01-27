package com.ufc.pix.service.impl;

import com.ufc.pix.dto.ViewGenerateReportsDto;
import com.ufc.pix.service.GenerateReportsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GenerateReportsServiceImpl implements GenerateReportsService {
    public GenerateReportsServiceImpl() {
    }

    @Override
    public Optional<ViewGenerateReportsDto> generateByUserId(UUID userId) {
        return Optional.empty();
    }
}
