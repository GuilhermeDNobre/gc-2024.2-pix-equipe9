package com.ufc.pix.dto;

import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.model.PixKey;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PixKeyView {

    private String key;
    private KeyType type;
    private LocalDate date;
    public PixKeyView(String key, KeyType type, LocalDate date) {
        this.key = key;
        this.type = type;
        this.date = date;
    }

    public PixKey toModel() {
        PixKey pixKey = new PixKey();
        pixKey.setKey(this.key);
        pixKey.setType(this.type);
        pixKey.setDate(this.date);
        return pixKey;
    }
}
