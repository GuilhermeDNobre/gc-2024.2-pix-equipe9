package com.ufc.pix.service;

import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.model.PixKey;
import com.ufc.pix.model.User;

import java.util.List;
import java.util.UUID;

public interface PixKeyService {
    PixKey generateRandomPixKey(User user);
    PixKey registerPixKey(User user, KeyType type, String Key);
    PixKey validatePixKey(String key);
    List<PixKey> findAllByUser(UUID id);
}
