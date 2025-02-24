package com.ufc.pix.controller;

import com.ufc.pix.model.UnlockRequest;
import com.ufc.pix.service.impl.UnlockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/unlock-requests")
public class UnlockRequestController {

    @Autowired
    private UnlockRequestService unlockRequestService;

    @PostMapping
    public ResponseEntity<UnlockRequest> requestUnlock(@RequestParam UUID pixKeyId, @RequestParam UUID userId) {
        UnlockRequest unlockRequest = unlockRequestService.requestUnlock(pixKeyId, userId);
        return ResponseEntity.ok(unlockRequest);
    }


}
