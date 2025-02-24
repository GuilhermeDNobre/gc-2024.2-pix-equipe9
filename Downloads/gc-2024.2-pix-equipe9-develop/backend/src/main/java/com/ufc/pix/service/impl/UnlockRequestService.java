package com.ufc.pix.service.impl;


import com.ufc.pix.model.UnlockRequest;
import com.ufc.pix.repository.UnlockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UnlockRequestService {

    @Autowired
    private UnlockRequestRepository unlockRequestRepository;

    public UnlockRequest requestUnlock(UUID pixKeyId, UUID userId) {
        UnlockRequest unlockRequest = new UnlockRequest();
        unlockRequest.setPixKeyId(pixKeyId);
        unlockRequest.setUserId(userId);
        unlockRequest.setStatus("PENDENTE"); // Status inicial

        return unlockRequestRepository.save(unlockRequest);
    }


}
