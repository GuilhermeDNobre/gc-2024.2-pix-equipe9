package com.ufc.pix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@Autowired
GenerateReportsService generateReportsService;
@RestController
@RequestMapping("/generate-reports")
public class GenerateReportsController {
    @GetMapping("/accounts/{id}")
    public ResponseEntity<ViewGenerateReportsDto> generateReports(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(this.generateReportsService.generateByUserId(id));
    }
}
