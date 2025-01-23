package com.ufc.pix.model;

import com.ufc.pix.dto.ViewPixKeyDto;
import com.ufc.pix.enumeration.KeyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class PixKey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String key;

    @Enumerated(EnumType.STRING)
    private KeyType type;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ViewPixKeyDto toView() {
        return new ViewPixKeyDto(this.key, this.type, this.date);
    }
}
