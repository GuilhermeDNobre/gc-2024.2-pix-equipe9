package com.ufc.pix.controller;

import com.ufc.pix.dto.ViewPixKeyDto;
import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.PixKey;
import com.ufc.pix.model.User;
import com.ufc.pix.service.PixKeyService;
import com.ufc.pix.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pix")
public class PixKeyController {

    @Autowired
    private PixKeyService pixKeyService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateRadomPixKey(
            @RequestHeader("Authorization") String token
    ) {
        User user = tokenService.getUserFromToken(token.substring(7));

        PixKey pixKey = pixKeyService.generateRandomPixKey(user);
        return ResponseEntity.ok(pixKey.getKey());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPixKey(
            @RequestHeader("Authorization") String token,
            @RequestParam KeyType type,
            @RequestParam(required = false) String key
    ) {
        User user = tokenService.getUserFromToken(token.substring(7));

        if (type.equals(KeyType.RANDOM)) {
            throw new BusinessException("Chave aleatoria não pode ser gerado nessa requisição!");
        }

        PixKey pixKey = pixKeyService.registerPixKey(user, type, key);
        return ResponseEntity.ok(pixKey.getKey());
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validatePixKey(@RequestParam String key) {
        PixKey pixKey = pixKeyService.validatePixKey(key);
        return ResponseEntity.ok(pixKey.toView());
    }

    @GetMapping("")
    public ResponseEntity<List<ViewPixKeyDto>> getAll(
            @RequestHeader("Authorization") String token
    ) {
        User user = tokenService.getUserFromToken(token.substring(7));
        return ResponseEntity
                .ok()
                .body(this.pixKeyService.findAllByUser(user.getId())
                        .stream()
                        .map(PixKey::toView)
                        .toList());
    }
}
