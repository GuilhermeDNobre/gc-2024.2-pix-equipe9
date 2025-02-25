package com.ufc.pix.controller;

import com.ufc.pix.dto.ViewGenerateReportsDto;
import com.ufc.pix.service.GenerateReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("generate-reports")
public class GenerateReportsController {

    @Autowired
    private GenerateReportsService generateReportsService;

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<ViewGenerateReportsDto>> generateReports(
            @PathVariable(value = "userId") UUID userId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate
    ) {
        LocalDateTime start = startDate != null ? LocalDateTime.parse(startDate) : null;
        LocalDateTime end = endDate != null ? LocalDateTime.parse(endDate) : null;

        Optional<ViewGenerateReportsDto> report = generateReportsService.generateByUserId(userId, start, end);
        return ResponseEntity.ok(report);
    }
}
