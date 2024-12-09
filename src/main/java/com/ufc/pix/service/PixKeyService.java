package com.ufc.pix.service;

import com.ufc.pix.model.PixKey;

public interface PixKeyService {
    PixKey generatePixKey(String userId, String type);
    boolean validatePixKey(String key);
}
