package com.ufc.pix.service.impl;

import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.PixKey;
import com.ufc.pix.model.User;
import com.ufc.pix.repository.PixKeyRepository;
import com.ufc.pix.service.PixKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PixKeyServiceImpl implements PixKeyService {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int KEY_LENGTH = 32;

    @Override
    public PixKey generateRandomPixKey(User user) {
        PixKey pixKey = new PixKey();
        pixKey.setAccount(user.getAccount());
        pixKey.setType(KeyType.RANDOM);
        pixKey.setDate(LocalDate.now());

        pixKey.setKeyValue(generateRandomKey());

        return pixKeyRepository.save(pixKey);
    }

    @Override
    public PixKey registerPixKey(User user, KeyType type, String key) {

        boolean hasPixKeyOfType = pixKeyRepository.existsByType(type);
        if (hasPixKeyOfType) {
            throw new BusinessException("Usuário já possui uma chave PIX cadastrada do tipo " + type);
        }

        PixKey pixKey = new PixKey();
        pixKey.setAccount(user.getAccount());
        pixKey.setType(type);
        pixKey.setKeyValue(key);
        pixKey.setDate(LocalDate.now());
        return pixKeyRepository.save(pixKey);
    }


    private String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        StringBuilder key = new StringBuilder(KEY_LENGTH);

        for (int i = 0; i < KEY_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            key.append(CHARACTERS.charAt(index));
        }

        return key.toString();
    }

    @Override
    public PixKey validatePixKey(String key) {
        Optional<PixKey> pixKey = pixKeyRepository.findByKeyValue(key);
        if (pixKey.isPresent()) {
            return pixKey.get();
        } else {
            throw new IllegalArgumentException("Chave PIX não encontrada");
        }
    }

    @Override
    public PixKey getById(UUID id) {
        return null;
    }

    @Override
    public List<PixKey> getAll() {
        return this.pixKeyRepository.findAll();
    }


}
