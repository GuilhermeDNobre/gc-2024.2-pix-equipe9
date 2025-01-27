package com.ufc.pix.service.impl;

import com.ufc.pix.dto.ViewPixKeyDto;
import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.PixKey;
import com.ufc.pix.model.User;
import com.ufc.pix.repository.AccountRepository;
import com.ufc.pix.repository.PixKeyRepository;
import com.ufc.pix.service.PixKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private AccountRepository accountRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int KEY_LENGTH = 32;

    @Override
    public void generateRandomPixKey(UUID userId) {

        PixKey pixKey = new PixKey();
        pixKey.setType(KeyType.RANDOM);
        pixKey.setDate(LocalDate.now());
        pixKey.setKeyValue(generateRandomKey());

        var userAccount = accountRepository.findByUserId(userId).orElseThrow(
                () -> new BusinessException("User does not have an account", HttpStatus.NOT_FOUND)
        );

        pixKey.setAccount(userAccount);
        pixKeyRepository.save(pixKey);
    }

    @Override
    public void registerPixKey(UUID userID, KeyType type, String key) {
        var userAccount = accountRepository.findByUserId(userID).orElseThrow(
                () -> new BusinessException("User does not have an account", HttpStatus.NOT_FOUND)
        );

        var hasPixKeyOfType = pixKeyRepository.findByAccount_IdAndType(userAccount.getId(),type);
        if (hasPixKeyOfType.isPresent()) {
            throw new BusinessException("User already has a registered PIX key of the type " + type);
        }


        PixKey pixKey = new PixKey();
        pixKey.setAccount(userAccount);
        pixKey.setType(type);
        pixKey.setKeyValue(key);
        pixKey.setDate(LocalDate.now());
        pixKeyRepository.save(pixKey);
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
    public List<ViewPixKeyDto> getAll() {
        return this.pixKeyRepository.findAll().stream().map(PixKey::toView).toList();
    }


}
