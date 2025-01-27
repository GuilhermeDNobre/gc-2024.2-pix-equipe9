package com.ufc.pix.repository;

import com.ufc.pix.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Query("select a from Notification a where a.userId = :id")
    List<Notification> findByUserId(@Param("id") UUID id);


    Notification save(Notification notification);
}
