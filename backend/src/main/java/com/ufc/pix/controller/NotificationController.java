package com.ufc.pix.controller;

import com.ufc.pix.model.Notification;
import com.ufc.pix.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationByUserId(@PathVariable UUID userId) {
        return  ResponseEntity.ok(notificationService.getNotificationByUserId(userId));
    }
}
