package com.ufc.pix.controller;

import com.ufc.pix.dto.PixKeyView;
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
            return ResponseEntity.badRequest().body("Chave aleatoria não pode ser gerado nessa requisição!");
        }

        try {
            PixKey pixKey = pixKeyService.registerPixKey(user, type, key);
            return ResponseEntity.ok(pixKey.getKey());
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validatePixKey(@RequestParam String key) {
//      https://dict-np-h.pi.rsfn.net.br:16532/api-np/v2/keys/check
        try {
            PixKey pixKey = pixKeyService.validatePixKey(key);
            return ResponseEntity.ok(pixKey.toView());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Chave PIX não encontrada");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<PixKeyView>> getAll(
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
