package com.ufc.pix.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAccess {

    USER("User"),
    ADMIN("Admin");

    private final String description;

}
