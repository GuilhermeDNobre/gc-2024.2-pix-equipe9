// NotificationService.java
package com.ufc.pix.service.impl;

import com.ufc.pix.model.Notification;
import com.ufc.pix.repository.NotificationRepository;
import com.ufc.pix.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    public List<Notification> getNotificationByUserId(UUID id) {
        return notificationRepository.findByUserId(id);
    }

    public Notification saveNotification(Notification notification) {
        if (notification.getNotificationId() == null) {
            notification.setNotificationId(UUID.randomUUID());
        }
        return notificationRepository.save(notification);
    }
}