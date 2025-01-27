package com.ufc.pix.controller;

import com.ufc.pix.dto.ViewGenerateReportsDto;
import com.ufc.pix.service.GenerateReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("generate-reports")
public class GenerateReportsController {
    @Autowired
    GenerateReportsService generateReportsService;

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<ViewGenerateReportsDto>> generateReports(@PathVariable(value = "userId") UUID userId) {
        return ResponseEntity.ok(this.generateReportsService.generateByUserId(userId));
    }
}
