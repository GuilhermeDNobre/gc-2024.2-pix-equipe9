package com.ufc.pix.controller;

import com.ufc.pix.dto.ViewLoginActivityDto;
import com.ufc.pix.model.Notification;
import com.ufc.pix.service.LoginActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/login-activity")
public class LoginActivityController {
    @Autowired
    private LoginActivityService loginActivityService;

    @GetMapping("/{userId}")
    public ResponseEntity<ViewLoginActivityDto> getAllByUserId(@PathVariable UUID userId) {
        return  ResponseEntity.ok(loginActivityService.listAllByUserId(userId).orElseThrow(() -> new RuntimeException("No login activity found")));
    }
}