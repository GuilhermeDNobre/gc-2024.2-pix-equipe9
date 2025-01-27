package com.ufc.pix.controller;

import com.ufc.pix.doc.PixKeyDoc;
import com.ufc.pix.dto.ViewPixKeyDto;
import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.PixKey;
import com.ufc.pix.service.PixKeyService;
import com.ufc.pix.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pix")
public class PixKeyController implements PixKeyDoc {

    @Autowired
    private PixKeyService pixKeyService;

    @Autowired
    private TokenServiceImpl tokenService;

    @PostMapping("/generate/{userId}")
    public ResponseEntity<Void> generateRandomPixKey(@PathVariable UUID userId) {
        pixKeyService.generateRandomPixKey(userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/register/{userId}")
    public ResponseEntity<Void> registerPixKey(
            @PathVariable UUID userId,
            @RequestParam KeyType type,
            @RequestParam(required = false) String key
    ) {

        if (type.equals(KeyType.RANDOM)) {
            throw new BusinessException("Chave aleatoria não pode ser gerado nessa requisição!");
        }

        pixKeyService.registerPixKey(userId, type, key);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/validate")
    public ResponseEntity<ViewPixKeyDto> validatePixKey(@RequestParam String key) {
        PixKey pixKey = pixKeyService.validatePixKey(key);
        return ResponseEntity.ok(pixKey.toView());
    }

    @GetMapping("")
    public ResponseEntity<List<ViewPixKeyDto>> getAll() {
        return ResponseEntity.ok(this.pixKeyService.getAll());
    }
}
