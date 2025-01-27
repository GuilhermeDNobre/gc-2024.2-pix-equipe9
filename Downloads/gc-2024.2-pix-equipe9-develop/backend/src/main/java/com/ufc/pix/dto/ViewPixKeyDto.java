package com.ufc.pix.dto;

import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.model.PixKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewPixKeyDto {

    private UUID id;
    private String key;
    private KeyType type;
    private LocalDate date;
    private UUID accountId;

    public PixKey toModel() {
        PixKey pixKey = new PixKey();
        pixKey.setKeyValue(this.key);
        pixKey.setType(this.type);
        pixKey.setDate(this.date);
        return pixKey;
    }
}
