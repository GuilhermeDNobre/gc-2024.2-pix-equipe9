package com.ufc.pix.repository;

import com.ufc.pix.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Query("select a from Notification a where a.user.id = :id")
    List<Notification> findByUserId(@Param("id") UUID id);
}
