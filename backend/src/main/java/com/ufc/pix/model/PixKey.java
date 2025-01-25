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
@Table(name = "pix_keys")
public class PixKey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String keyValue;
    @Enumerated(EnumType.STRING)
    private KeyType type;
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    public ViewPixKeyDto toView() {
        return new ViewPixKeyDto(
                getId(),
                getKeyValue(),
                this.type,
                this.date,
                getAccount().getId()
        );
    }
}
