package com.ufc.pix.service.impl;

import com.ufc.pix.model.PixKey;
import com.ufc.pix.repository.PixKeyRepository;
import com.ufc.pix.service.PixKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PixKeyServiceImpl implements PixKeyService {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @Override
    public PixKey generatePixKey(String userId, String type) {
        PixKey pixKey = new PixKey();
        pixKey.setUserId(userId);
        pixKey.setType(type);

        // Gerar chave aleatória para tipo "Aleatória"
        if ("random".equalsIgnoreCase(type)) {
            pixKey.setKey(UUID.randomUUID().toString());
        } else {
            throw new RuntimeException("Invalid key type provided.");
        }

        return pixKeyRepository.save(pixKey);
    }

    @Override
    public boolean validatePixKey(String key) {
        return pixKeyRepository.findByKey(key).isPresent();
    }
}
