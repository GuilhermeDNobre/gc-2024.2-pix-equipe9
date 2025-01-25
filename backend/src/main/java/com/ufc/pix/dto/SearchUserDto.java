package com.ufc.pix.dto;

import com.ufc.pix.enumeration.UserAccess;
import com.ufc.pix.enumeration.UserStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchUserDto {

    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private UserStatus status;
    private UserAccess access;

}
