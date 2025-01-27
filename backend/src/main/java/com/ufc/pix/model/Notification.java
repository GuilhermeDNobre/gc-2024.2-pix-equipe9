package com.ufc.pix.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity()
public class Notification {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private LocalDateTime finishedAt;

}
