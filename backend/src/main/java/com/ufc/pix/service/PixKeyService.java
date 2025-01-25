package com.ufc.pix.service;

import com.ufc.pix.dto.ViewPixKeyDto;
import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.model.PixKey;
import java.util.List;

import java.util.UUID;

public interface PixKeyService {
    void generateRandomPixKey(UUID userId);
    void registerPixKey(UUID userId, KeyType type, String Key);
    PixKey validatePixKey(String key);
    PixKey getById(UUID id);
    List<ViewPixKeyDto> getAll();
}
