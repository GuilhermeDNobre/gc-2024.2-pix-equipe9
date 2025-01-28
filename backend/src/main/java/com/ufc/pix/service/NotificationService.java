package com.ufc.pix.service;

import com.ufc.pix.model.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    List<Notification> getNotificationByUserId(UUID id);

    void saveNotification(Notification notification);
}
