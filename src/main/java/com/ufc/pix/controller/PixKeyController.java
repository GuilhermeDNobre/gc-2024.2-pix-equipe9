package com.ufc.pix.controller;

import com.ufc.pix.model.PixKey;
import com.ufc.pix.service.PixKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pix")
public class PixKeyController {

    @Autowired
    private PixKeyService pixKeyService;

    @PostMapping("/generate")
    public ResponseEntity<PixKey> generatePixKey(@RequestParam String userId, @RequestParam String type) {
        PixKey pixKey = pixKeyService.generatePixKey(userId, type);
        return ResponseEntity.ok(pixKey);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validatePixKey(@RequestParam String key) {
        boolean isValid = pixKeyService.validatePixKey(key);
        return ResponseEntity.ok(isValid);
    }
}
