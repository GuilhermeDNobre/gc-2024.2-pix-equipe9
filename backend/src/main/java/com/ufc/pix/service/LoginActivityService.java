package com.ufc.pix.service;

import com.ufc.pix.dto.ViewLoginActivityDto;
import java.util.Optional;
import java.util.UUID;

public interface LoginActivityService {
    void registerLogin(UUID userId);
    Optional<ViewLoginActivityDto> listAllByUserId(UUID userId);
}
